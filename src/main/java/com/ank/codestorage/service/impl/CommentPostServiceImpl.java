package com.ank.codestorage.service.impl;

import com.ank.codestorage.dto.CommentPostDto;
import com.ank.codestorage.dto.InputCommentPostDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.exception.NotFoundException;
import com.ank.codestorage.mapper.CommentPostMapper;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.User;
import com.ank.codestorage.repositorie.CommentPostRepository;
import com.ank.codestorage.service.CommentPostService;
import com.ank.codestorage.service.PostService;
import com.ank.codestorage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentPostServiceImpl implements CommentPostService {
    private final CommentPostRepository commentPostRepository;
    private final UserService userService;
    private final PostService postService;
    private final CommentPostMapper mapper;

//    @Override
//    public CommentPost findById(Integer id) {
//        return commentPostRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
//    }

    @Override
    public PageDto<CommentPostDto> findByPostId(int postId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "dateCreate"));
        Page<CommentPost> page =  commentPostRepository.findByPost_id(postId, pageable);

        return mapper.mapToPageCommentPostDto(page);
    }

    //    @Override
//    public List<CommentPost> findAll() {
//       return commentPostRepository.findAll();
//    }
    @Transactional
    @Override
    public CommentPostDto create(InputCommentPostDto inputCommentPostDto) {
        CommentPost commentPost = new CommentPost();
        commentPost.setUser(userService.findById(inputCommentPostDto.getUserId()));
        commentPost.setPost(postService.findById(inputCommentPostDto.getPostId()));
        commentPost.setText(inputCommentPostDto.getText());
        commentPost.setDateCreate(LocalDateTime.now());

        return mapper.mapToCommentPostDto(commentPostRepository.save(commentPost));
    }

    @Transactional
    @Override
    public void delete(int id) {
        CommentPost commentPost = commentPostRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        commentPostRepository.delete(commentPost);
    }

    private NotFoundException throwNotFoundException(Integer id) {
        String message = "Пост с id " + id + " не найден!.";
        log.warn(message);
        return new NotFoundException(message);
    }
}
