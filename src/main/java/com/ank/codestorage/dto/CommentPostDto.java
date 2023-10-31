package com.ank.codestorage.dto;

import com.ank.codestorage.model.Post;
import com.ank.codestorage.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Данные о посте
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPostDto {
    private int id;
    private String text;
    private String userLogin;
    private int userId;
    private int postId;
    private LocalDateTime dateCreate;
}
