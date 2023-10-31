package com.ank.codestorage.service;

import com.ank.codestorage.dto.GradePostDto;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.GradePost;

import java.util.List;
import java.util.Optional;

public interface GradePostService {
    GradePost create(GradePostDto gradePostDto);

    /**
     * Средняя оценка поста
     * @param postId - ид поста
     * @return оценка
     */
    double avgGrage(int postId);

    /**
     *
     * @param userId
     * @param postId
     * @return
     */
    GradePost findByUserIdAndPostId(int userId, int postId);
}
