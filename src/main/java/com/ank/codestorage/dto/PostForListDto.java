package com.ank.codestorage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostForListDto {
    private Integer id;
    private String title;
    private LocalDateTime dateChange;
    private String userLogin;
    private Integer userId;
    private Double grade;
}
