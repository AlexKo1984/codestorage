package com.ank.codestorage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Входящие данные о пользователе
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}

