package com.ank.codestorage.dto;

import com.ank.codestorage.model.Post;
import com.ank.codestorage.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные об оценке поста
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradePostDto {
//    @NotNull
//    private User user;
//    @NotNull
//    private Post post;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer postId;
    @Min(1)
    @Max(5)
    private Integer value;//Значение оценки
}
