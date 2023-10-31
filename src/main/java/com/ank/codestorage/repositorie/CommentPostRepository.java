package com.ank.codestorage.repositorie;

import com.ank.codestorage.dto.CommentPostDto;
import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.LangCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
    Page<CommentPost> findByPost_id(int post_id, Pageable pageable);
}
