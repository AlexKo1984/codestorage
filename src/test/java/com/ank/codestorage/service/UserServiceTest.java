package com.ank.codestorage.service;

import com.ank.codestorage.exception.UserAlreadyExistException;
import com.ank.codestorage.mapper.UserMapper;
import com.ank.codestorage.model.User;
import com.ank.codestorage.model.enums.TypeUser;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:test.properties")
class UserServiceTest {
    private final UserService userService;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("GIVEN данные 2х пользователей с одинаковым логином" +
            " WHEN создаем пользователей" +
            " THEN Ошибка при создании пользователя")
    @Test
    void shouldExceptionWhenCreateUser() {
        User user = new User(1, "test", "test", "test@q.ru", "password", TypeUser.USER);
        userService.create(user);

        final User user2 = new User(2, "test", "test", "test@q.ru", "password", TypeUser.USER);

        UserAlreadyExistException exp = assertThrows(UserAlreadyExistException.class,
                ()->userService.create(user2));

        assertFalse(exp.getMessage().isEmpty());
    }

    @DisplayName("GIVEN данные пользователя" +
            " WHEN создаем пользователя" +
            " WHEN создан пользователь")
    @Test
    void shouldReturnUserWhenCreateUser() {
        String email = "test@q.ru";
        User user = new User(1, "test", "test", email, "password", TypeUser.USER);
        User newUser = userService.create(user);

        assertEquals(email, newUser.getEmail());
    }
}