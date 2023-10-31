package com.ank.codestorage.controller;

import com.ank.codestorage.dto.*;
import com.ank.codestorage.mapper.CommentPostMapper;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.service.CommentPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentPostController{
    private final CommentPostService commentPostService;
    private final CommentPostMapper mapper;

    /**
     * Комментарии к посту с пагинацией
     * @param postId
     * @return
     */
    @GetMapping("pages/{postId}")
    public PageDto<CommentPostDto> findByPostId(@PathVariable Integer postId,
                                                @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                @Positive @RequestParam(defaultValue = "10") Integer size) {
        return commentPostService.findByPostId(postId, from, size);
    }
    @PostMapping()
    public CommentPostDto create(@Valid @RequestBody InputCommentPostDto inputCommentPostDto) {
        return commentPostService.create(inputCommentPostDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        commentPostService.delete(id);
    }
}
