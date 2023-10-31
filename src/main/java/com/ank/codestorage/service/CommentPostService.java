package com.ank.codestorage.service;

import com.ank.codestorage.dto.CommentPostDto;
import com.ank.codestorage.dto.InputCommentPostDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.Post;

import java.util.List;

public interface CommentPostService {
    //CommentPost findById(int id);
    PageDto<CommentPostDto> findByPostId(int postId, int pageNumber, int pageSize);

    CommentPostDto create(InputCommentPostDto inputCommentPostDto);

    void delete(int id);
}
