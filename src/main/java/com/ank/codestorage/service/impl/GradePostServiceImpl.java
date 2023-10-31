package com.ank.codestorage.service.impl;

import com.ank.codestorage.dto.GradePostDto;
import com.ank.codestorage.exception.NotFoundException;
import com.ank.codestorage.model.GradePost;
import com.ank.codestorage.repositorie.GradePostRepository;
import com.ank.codestorage.service.GradePostService;
import com.ank.codestorage.service.PostService;
import com.ank.codestorage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GradePostServiceImpl implements GradePostService {
    private final GradePostRepository gradePostRepository;
    private final UserService userService;
    private final PostService postService;

    @Transactional
    @Override
    public GradePost create(GradePostDto gradePostDto) {
        GradePost gradePost = new GradePost();
        gradePost.setUser(userService.findById(gradePostDto.getUserId()));
        gradePost.setPost(postService.findById(gradePostDto.getPostId()));
        gradePost.setValue(gradePostDto.getValue());

        return gradePostRepository.save(gradePost);
    }

    @Override
    public double avgGrage(int postId) {
        Double grage = gradePostRepository.avgGrage(postId);
        return grage == null ? 0 : grage;
    }
    @Override
    public GradePost findByUserIdAndPostId(int userId, int postId) {
        return gradePostRepository.findByUser_idAndPost_id(userId, postId).orElseThrow(()-> throwNotFoundException(userId, postId));
    }

    private NotFoundException throwNotFoundException(int userId, int postId) {
        String message = "Нет оценки пользователя с id: " + userId + " для поста с id " + postId;
        log.warn(message);
        return new NotFoundException(message);
    }
}
