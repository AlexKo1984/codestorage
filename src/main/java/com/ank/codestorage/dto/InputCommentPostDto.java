package com.ank.codestorage.dto;

import com.ank.codestorage.model.Post;
import com.ank.codestorage.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Данные при создании комментария
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputCommentPostDto {
    @NotBlank
    private String text;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer postId;
}
