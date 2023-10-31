package com.ank.codestorage.controller;

import com.ank.codestorage.dto.GradePostDto;
import com.ank.codestorage.dto.PostDto;
import com.ank.codestorage.mapper.GradePostMapper;
import com.ank.codestorage.model.GradePost;
import com.ank.codestorage.model.Post;
import com.ank.codestorage.service.GradePostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade-post")
@RequiredArgsConstructor
public class GradePostController {
    private final GradePostService service;
    private final GradePostMapper mapper;

    @GetMapping("/user/{userId}/post/{postId}")
    public GradePostDto findById(@PathVariable Integer userId, @PathVariable Integer postId) {
        return mapper.mapToGradePostDto(service.findByUserIdAndPostId(userId, postId));
    }
    @PostMapping()
    public void create(@Valid @RequestBody GradePostDto gradePostDto) {
        GradePost gradePost = service.create(gradePostDto);
    }

}
