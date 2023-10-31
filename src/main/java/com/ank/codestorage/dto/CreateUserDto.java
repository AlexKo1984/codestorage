package com.ank.codestorage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Создание пользователя
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Имя не должно быть пустым")
    private String name;
    @NotBlank(message = "Логин не должне быть пустым")
    private String login;
    @NotBlank
    @Email(message = "Неправильный формат почты")
    private String email;
    @NotBlank(message = "Пароль не должне быть пустым")
    private String password;
    @NotBlank(message = "Тип пользователя не должне быть пустым")
    private String typeUser;
}
