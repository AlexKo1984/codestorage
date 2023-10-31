package com.ank.codestorage.controller;

import com.ank.codestorage.dto.CreateUserDto;
import com.ank.codestorage.dto.UserDto;
import com.ank.codestorage.mapper.UserMapper;
import com.ank.codestorage.model.User;
import com.ank.codestorage.model.enums.TypeUser;
import com.ank.codestorage.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест для демонстрации тестирования с мок объектами
 */
@WebMvcTest(controllers = UserController.class)
class UserControllerMvcTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void setUp() {
        //Если не добавить то ошибка Forbidden 403
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("GIVEN данные для создания пользователя" +
            " WHEN создаем пользователя" +
            " THEN создаем пользователя и возвращаем данные после создания")
    @Test
    void create() throws Exception {
        //GIVEN - дано
        User user = new User(1, "test", "test", "test@q.ru", "password", TypeUser.USER);
        CreateUserDto createUserDto = new CreateUserDto("test", "test", "test@q.ru", "password", "USER");
        UserDto userDto = new UserDto(1, "test", "test", "test@q.ru", "USER");

        when(userMapper.mapToUser(any(CreateUserDto.class)))
                .thenReturn(user);
        when(userService.create(any(User.class)))
                .thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class)))
                .thenReturn(userDto);
        //WHEN - совершаем действие
        mvc.perform(post("/user")
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