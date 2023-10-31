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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class CommentPostControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @DisplayName("GIVEN данные для создания комментария поста" +
            " WHEN создаем комментарий поста" +
            " THEN создаем комментарий и возвращаем данные после создания")
    @Test
    void shouldCreateCommentPost() throws Exception {
        //GIVEN - дано
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 10, 0, 0);

        InputCommentPostDto inputCommentPostDto = new InputCommentPostDto("text 123", 1, 1);
        CommentPostDto commentPostDto = new CommentPostDto(7, "text 123", "veter", 1, 1, date);

        //WHEN - совершаем действие
        mvc.perform(post("/comment")
                        .header("Authorization", getBasicAuthenticationHeader())
                        .content(mapper.writeValueAsString(inputCommentPostDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(commentPostDto.getText())))
                .andExpect(jsonPath("$.userLogin", is(commentPostDto.getUserLogin())))
                .andExpect(jsonPath("$.userId", is(commentPostDto.getUserId()), Integer.class))
                .andExpect(jsonPath("$.postId", is(commentPostDto.getPostId()), Integer.class));
    }

    @DisplayName("GIVEN данные для поиска страницы комментариев" +
            " WHEN ищем комментарий по размеру страницы и начальной странице" +
            " THEN возвращаем данные страницы комментариев")
    @Test
    void shouldReturnPageCommentPosts() throws Exception {
        //GIVEN - дано
        CommentPostDto commentPostDto = new CommentPostDto(7, "text 1", "veter", 1, 1, null);

        List<CommentPostDto> content = List.of(commentPostDto);
        PageDto<CommentPostDto> pageDto = new PageDto<>(3, content, 1, 2, 2);


        //WHEN - совершаем действие
        mvc.perform(get("/comment/pages/1?from=1&size=2")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(pageDto.getTotal()), Long.class))
                .andExpect(jsonPath("$.pageNumber", is(pageDto.getPageNumber()), Integer.class))
                .andExpect(jsonPath("$.pageSize", is(pageDto.getPageSize()), Integer.class))
                .andExpect(jsonPath("$.totalPage", is(pageDto.getTotalPage()), Integer.class))
                .andExpect(jsonPath("$.content[0].text",  is(commentPostDto.getText())))
                .andExpect(jsonPath("$.content[0].userLogin", is(commentPostDto.getUserLogin())))
                .andExpect(jsonPath("$.content[0].userId", is(commentPostDto.getUserId()), Integer.class))
                .andExpect(jsonPath("$.content[0].postId", is(commentPostDto.getPostId()), Integer.class));
    }
    private String getBasicAuthenticationHeader() {
        String username = "veter";
        String password = "123";
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
