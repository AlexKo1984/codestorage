package com.ank.codestorage.dto;

import com.ank.codestorage.model.enums.TypeUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    @NotBlank
    private String name;

    @NotBlank
    private String login;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String typeUser;
}
