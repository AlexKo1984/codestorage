package com.ank.codestorage.repositorie;

import com.ank.codestorage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByLoginAndPassword(String login, String password);
}


