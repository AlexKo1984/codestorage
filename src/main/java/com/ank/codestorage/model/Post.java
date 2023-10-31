package com.ank.codestorage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Пост пользователя с кодом
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String code;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;
    /**
     * Дата создания
     */
    @Column(name = "date_create",nullable = false)
    private LocalDateTime dateCreate;
    /**
     * Дата обновления
     */
    @Column(name = "date_change", nullable = false)
    private LocalDateTime dateChange;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    /**
     * Язык кода
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "lang_code_id", nullable = false)
    private LangCode langCode;

    /**
     * Средняя оценка поста
     */
    @Transient
    private double avgGrade;
}
