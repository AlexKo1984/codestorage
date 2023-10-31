package com.ank.codestorage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Оценка поста
 */
@Getter
@Setter
@Entity
@Table(name = "grade_post", schema = "public")
public class GradePost{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Min(1)
    @Max(5)
    @JoinColumn(name = "value", nullable = false)
    private Integer value;//Значение оценки
}
