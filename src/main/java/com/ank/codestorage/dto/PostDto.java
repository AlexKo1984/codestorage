package com.ank.codestorage.dto;

import com.ank.codestorage.model.LangCode;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer id;
    @NotBlank
    private String code;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateChange;
    private ShortUserDto owner;
    private LangCodeDto langCode;
    /**
     * Средняя оценка поста
     */
    private double avgGrade;
}
