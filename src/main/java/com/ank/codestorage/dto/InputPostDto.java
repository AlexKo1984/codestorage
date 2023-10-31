package com.ank.codestorage.dto;

import com.ank.codestorage.model.LangCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные для создания/обновления поста
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputPostDto {
    @NotBlank
    private String code;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Integer userId;
    private Integer langCodeId;
}
