package com.ank.codestorage.repositorie;

import com.ank.codestorage.dto.PostForListDto;
import com.ank.codestorage.model.Post;
import com.ank.codestorage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
