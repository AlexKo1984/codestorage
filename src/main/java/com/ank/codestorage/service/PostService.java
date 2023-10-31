package com.ank.codestorage.service;

import com.ank.codestorage.dto.InputPostDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.dto.PostForListDto;
import com.ank.codestorage.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    Post findById(Integer id);

    Post create(InputPostDto inputPost);

    Post update(Integer id, Post inputPostDto);

    void delete(Integer id);

    PageDto<PostForListDto> findPostForList(int pageNumber, int pageSize,int idLangCode, String subString);
}
