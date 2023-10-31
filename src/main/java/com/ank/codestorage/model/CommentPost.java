package com.ank.codestorage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment_post", schema = "public")
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String text;
    /**
     * Дата создания
     */
    @Column(name = "date_create",nullable = false)
    private LocalDateTime dateCreate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}