package com.ank.codestorage.service.impl;

import com.ank.codestorage.dto.InputPostDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.dto.PostForListDto;
import com.ank.codestorage.exception.NotFoundException;
import com.ank.codestorage.model.Post;
import com.ank.codestorage.repositorie.PostRepository;
import com.ank.codestorage.service.GradePostService;
import com.ank.codestorage.service.LangCodeService;
import com.ank.codestorage.service.PostService;
import com.ank.codestorage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final LangCodeService langCodeService;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));

        return post;
    }

    /**
     * Поиск постов с пагинацие и фильтром по оценке оценкой
     * @param pageNumber - номер страницы
     * @param pageSize - размер страницы
     * @param subString - подстрока поиска в коде
     * @return страница постов
     */
    @Override
    public PageDto<PostForListDto> findPostForList(int pageNumber, int pageSize, int idLangCode, String subString){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        String filter = subString.isEmpty() ? "" : " and p.code like ?";
        subString = subString.isEmpty() ? "": "%" + subString + "%";
        String rowCountSql = "SELECT count(*) from post as p WHERE p.lang_code_id = " + idLangCode + "" + filter;

        int total = 0;
        if (filter.isEmpty())
            total = jdbcTemplate.<Integer>queryForObject(rowCountSql, Integer.class);
        else
            total = jdbcTemplate.<Integer>queryForObject(rowCountSql, Integer.class, subString);

        String sql = "select p.id, p.title, u.login as userLogin, u.id as userId, p.date_change,  avg(g.value) as grade" +
                " from (" +
                "   Select t.id, t.title, t.user_id, t.date_change From " +
                "       (Select p.id, p.title, p.user_id, p.date_change From post as p" +
                "       WHERE lang_code_id = " + idLangCode + "" + filter +
                "       Order by p.date_change desc) as t" +
                "   LIMIT " + pageable.getPageSize() +
                "   OFFSET " + pageable.getOffset()  +
                " ) as p" +
                " left join public.user as u on u.id = p.user_id" +
                " left join grade_post as g on p.id = g.post_id" +
                " GROUP BY p.id, p.title, u.login, u.id, p.date_change" +
                " Order by p.date_change desc";
        List<PostForListDto> posts;

        if (filter.isEmpty())
            posts = jdbcTemplate.query(sql, this::mapToPostForListDto);
        else
            posts = jdbcTemplate.query(sql, this::mapToPostForListDto, subString);

        int totalPage = (int)((total % pageSize) == 0 ? total / pageSize : (total / pageSize) + 1);

        return new PageDto<PostForListDto>(total, posts, pageNumber, pageSize, totalPage);
    }

    private PostForListDto mapToPostForListDto(ResultSet resultSet, int rowNum) throws SQLException {
        return PostForListDto.builder()
                .id(resultSet.getInt("id"))
                .grade(resultSet.getDouble("grade"))
                .title(resultSet.getString("title"))
                .userLogin(resultSet.getString("userLogin"))
                .userId(resultSet.getInt("userId"))
                .dateChange(resultSet.getObject("date_change", LocalDateTime.class))
                .build();
    }

    @Transactional
    @Override
    public Post create(InputPostDto inputPost) {
        Post post = new Post();
        post.setTitle(inputPost.getTitle());
        post.setDescription(inputPost.getDescription());
        post.setCode(inputPost.getCode());
        post.setOwner(userService.findById(inputPost.getUserId()));
        post.setLangCode(langCodeService.findById(inputPost.getLangCodeId()));
        post.setDateCreate(LocalDateTime.now());
        post.setDateChange(LocalDateTime.now());
        post = postRepository.save(post);

        log.info("Создан пост с ид: " + post.getId());

        return post;
    }

    @Transactional
    @Override
    public Post update(Integer id, Post inputPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        post.setCode(inputPost.getCode());
        post.setTitle(inputPost.getTitle());
        post.setDescription(inputPost.getDescription());
        post.setDateChange(LocalDateTime.now());

        log.info("Обновление поста с ид: " + id);

        return postRepository.save(post);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        postRepository.delete(post);
        log.info("Удален пост с ид: " + id);
    }

    private NotFoundException throwNotFoundException(Integer id) {
        String message = "Пост с id " + id + " не найден!.";
        log.warn(message);
        return new NotFoundException(message);
    }
}
