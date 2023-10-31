package com.ank.codestorage.model;

import com.ank.codestorage.model.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @NotBlank
    @Pattern(regexp="\\S+")
    @Column(nullable = false)
    private String login;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(name = "type_user")
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;   //Тип пользователя
}