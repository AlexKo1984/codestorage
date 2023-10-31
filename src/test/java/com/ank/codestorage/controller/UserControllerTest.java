package com.ank.codestorage.controller;

import com.ank.codestorage.dto.CreateUserDto;
import com.ank.codestorage.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("GIVEN данные для создания пользователя" +
            " WHEN создаем пользователя" +
            " THEN создаем пользователя и возвращаем данные после создания")
    @Test
    void shouldCreateUser() throws Exception {
        //GIVEN - дано
        CreateUserDto createUserDto = new CreateUserDto("test", "test", "test@q.ru", "password", "USER");
        UserDto userDto = new UserDto(2, "test", "test", "test@q.ru", "USER");

        //WHEN - совершаем действие
        mvc.perform(post("/user")
                        .content(mapper.writeValueAsString(createUserDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(userDto.getId()), Integer.class))
                .andExpect(jsonPath("$.name", is(userDto.getName())))
                .andExpect(jsonPath("$.login", is(userDto.getLogin())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                .andExpect(jsonPath("$.typeUser", is(userDto.getTypeUser())));
    }

    @DisplayName("GIVEN данные для поиска пользователя" +
            " WHEN ищем пользователя по ид" +
            " THEN возвращаем данные найденного пользователя")
    @Test
    void shouldReturnUserWhenGetUserById() throws Exception {
        //GIVEN - дано
        //Создан data-test.sql
        UserDto userDto = new UserDto(1, "Сергей Ветров", "veter", "veter@q.ru", "ADMIN");

        //WHEN - совершаем действие
        mvc.perform(get("/user/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1), Integer.class))
                .andExpect(jsonPath("$.name", is(userDto.getName())))
                .andExpect(jsonPath("$.login", is(userDto.getLogin())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                .andExpect(jsonPath("$.typeUser", is(userDto.getTypeUser())));
    }

    @DisplayName("GIVEN данные для аутентификации пользователя" +
            " WHEN ищем пользователя по логин/пароль" +
            " THEN возвращаем данные найденного пользователя")
    @Test
    void authenticationUser() throws Exception {
        //GIVEN - дано
        //Создан data-test.sql
        UserDto userDto = new UserDto(1, "Сергей Ветров", "veter", "veter@q.ru", "ADMIN");
        String password = "$2a$12$Ge3C8bfSSQbimNb1YqbUce9cm8jT8wJPr2IUR0hvZP.5LZPqfEq3K";

        //WHEN - совершаем действие
        mvc.perform(get("/user/au/veter")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-Code-Storage-User-Password", password))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1), Integer.class))
                .andExpect(jsonPath("$.name", is(userDto.getName())))
                .andExpect(jsonPath("$.login", is(userDto.getLogin())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                .andExpect(jsonPath("$.typeUser", is(userDto.getTypeUser())));
    }

    @DisplayName("GIVEN данные пользователя с существующим в базе логином" +
            " WHEN создаем пользователя" +
            " THEN Ошибка при создании пользователя")
    @Test
    void shouldExceptionWhenCreateUserOnExistingLogin() throws Exception {
        //GIVEN - дано
        CreateUserDto createUserDto = new CreateUserDto("test", "veter", "test@q.ru", "password", "USER");

        //WHEN - совершаем действие
        mvc.perform(post("/user")
                        .content(mapper.writeValueAsString(createUserDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error", is("Пользователь с логином veter уже зарегистрирован.")));
    }

    @DisplayName("GIVEN ид для удаления пользователя" +
            " WHEN удаляем пользователя " +
            " THEN пользователь удален")
    @Test
    void shouldDeleteUser() throws Exception{
        //WHEN - совершаем действие
        mvc.perform(delete("/user/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk());

    }
    @DisplayName("GIVEN данные для обновления пользователя" +
            " WHEN обновляем пользователя" +
            " THEN пользователь обновлен")
    @Test
    void shouldUpdateUser() throws Exception{
        //GIVEN - дано
        CreateUserDto createUserDto = new CreateUserDto("Вася", "veter", "veter@q.ru", "", "ADMIN");
        UserDto userDto = new UserDto(1, "Вася", "veter", "veter@q.ru", "ADMIN");
        //WHEN - совершаем действие
        mvc.perform(patch("/user/1")
                        .content(mapper.writeValueAsString(createUserDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto.getId()), Integer.class))
                .andExpect(jsonPath("$.name", is(userDto.getName())))
                .andExpect(jsonPath("$.login", is(userDto.getLogin())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                .andExpect(jsonPath("$.typeUser", is(userDto.getTypeUser())));
    }
}