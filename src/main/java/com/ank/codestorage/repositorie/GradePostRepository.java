package com.ank.codestorage.repositorie;

import com.ank.codestorage.model.CommentPost;
import com.ank.codestorage.model.GradePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GradePostRepository extends JpaRepository<GradePost, Integer> {
    @Query(value = "Select avg(value) From grade_post Where post_Id = ?1", nativeQuery = true)
    Double avgGrage(int postId);

    Optional<GradePost> findByUser_idAndPost_id(int userId, int postId);
}
