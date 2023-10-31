package com.ank.codestorage.controller;
import com.ank.codestorage.dto.*;
import com.ank.codestorage.mapper.PostMapper;
import com.ank.codestorage.model.Post;
import com.ank.codestorage.service.GradePostService;
import com.ank.codestorage.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final GradePostService gradePostService;
    private final PostMapper postMapper;

    @GetMapping
    public PageDto<PostForListDto> findAll(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                           @Positive @RequestParam(defaultValue = "10") Integer size,
                                           @Positive @RequestParam(name = "id-lang-code", required = true) Integer idLangCode,
                                           @Positive @RequestParam(name = "sub-str", defaultValue = "") String subString) {
        //return postMapper.mapToPostDto(postService.findAll());
        return postService.findPostForList(from, size, idLangCode, subString);
    }

    @GetMapping("/{id}")
    public PostDto findById(@PathVariable Integer id) {
        Post post = postService.findById(id);
        post.setAvgGrade(gradePostService.avgGrage(id));

        return postMapper.mapToPostDto(post);
    }

    @PostMapping("")
    public PostDto create(@Valid @RequestBody InputPostDto inputPost) {
        return postMapper.mapToPostDto(postService.create(inputPost));
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable Integer id, @Valid @RequestBody InputPostDto inputPost) {
        Post post = postMapper.mapToPost(inputPost);
        return postMapper.mapToPostDto(postService.update(id, post));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }
}
