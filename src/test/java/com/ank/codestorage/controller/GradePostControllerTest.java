package com.ank.codestorage.controller;

import com.ank.codestorage.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class GradePostControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @DisplayName("GIVEN данные для создания оценки поста" +
            " WHEN создаем оценку поста" +
            " THEN создаем оценку поста и возвращаем 200")
    @Test
    void shouldCreateGradePost() throws Exception {
        //GIVEN - дано
        GradePostDto gradePostDto = new GradePostDto(1, 3, 3);
        //WHEN - совершаем действие
        mvc.perform(post("/grade-post")
                        .header("Authorization", getBasicAuthenticationHeader())
                        .content(mapper.writeValueAsString(gradePostDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk());
    }
    @DisplayName("GIVEN данные для поиска оценки поста пользователем" +
            " WHEN ищем оценку по userId, postId" +
            " THEN возвращаем данные оценки")
    @Test
    void shouldReturnGradePost() throws Exception {
        //GIVEN - дано
        //Создан data-test.sql
        GradePostDto gradePostDto = new GradePostDto(1, 1, 3);

        //WHEN - совершаем действие
        mvc.perform(get("/grade-post/user/1/post/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(gradePostDto.getUserId()), Integer.class))
                .andExpect(jsonPath("$.postId", is(gradePostDto.getPostId())))
                .andExpect(jsonPath("$.value", is(gradePostDto.getValue())));
    }
    private String getBasicAuthenticationHeader() {
        String username = "veter";
        String password = "123";
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
