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
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class PostControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @DisplayName("GIVEN данные для поиска поста" +
            " WHEN ищем пост по ид" +
            " THEN возвращаем данные найденного поста")
    @Test
    void shouldReturnPostWhenGetPostById() throws Exception {
        //GIVEN - дано
        //Создан data-test.sql
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 10, 0, 0);
        ShortUserDto oowner = new ShortUserDto(1, "veter");
        LangCodeDto langCode = new LangCodeDto(1, "java", "java");
        PostDto postDto = new PostDto(1, "code 1", "title 1", "description 1",
                date, date, oowner, langCode, 0);

        //WHEN - совершаем действие
        mvc.perform(get("/post/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(postDto.getId()), Integer.class))
                .andExpect(jsonPath("$.code", is(postDto.getCode())))
                .andExpect(jsonPath("$.title", is(postDto.getTitle())))
                .andExpect(jsonPath("$.description", is(postDto.getDescription())))
                .andExpect(jsonPath("$.owner.id", is(postDto.getOwner().getId()), Integer.class))
                .andExpect(jsonPath("$.owner.login", is(postDto.getOwner().getLogin())))
                .andExpect(jsonPath("$.langCode.id", is(postDto.getLangCode().getId())))
                .andExpect(jsonPath("$.langCode.name", is(postDto.getLangCode().getName())));
    }

    @DisplayName("GIVEN данные для создания поста" +
            " WHEN создаем пост" +
            " THEN создаем пост и возвращаем данные после создания")
    @Test
    void shouldCreatePost() throws Exception {
        //GIVEN - дано
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 10, 0, 0);
        ShortUserDto oowner = new ShortUserDto(1, "veter");
        LangCodeDto langCode = new LangCodeDto(1, "java", "java");
        PostDto postDto = new PostDto(6, "code", "title", "description",
                date, date, oowner, langCode, 0);

        InputPostDto inputPostDto = new InputPostDto("code", "title", "description", 1, 1);
        //WHEN - совершаем действие
        mvc.perform(post("/post")
                        .header("Authorization", getBasicAuthenticationHeader())
                        .content(mapper.writeValueAsString(inputPostDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(postDto.getId()), Integer.class))
                .andExpect(jsonPath("$.code", is(postDto.getCode())))
                .andExpect(jsonPath("$.title", is(postDto.getTitle())))
                .andExpect(jsonPath("$.description", is(postDto.getDescription())))
                .andExpect(jsonPath("$.owner.id", is(postDto.getOwner().getId()), Integer.class))
                .andExpect(jsonPath("$.owner.login", is(postDto.getOwner().getLogin())))
                .andExpect(jsonPath("$.langCode.id", is(postDto.getLangCode().getId())))
                .andExpect(jsonPath("$.langCode.name", is(postDto.getLangCode().getName())));
    }

    @DisplayName("GIVEN данные для обновления поста" +
            " WHEN обновляем пост" +
            " THEN пост обновлен, вернули обновленные данные")
    @Test
    void shouldUpdateUser() throws Exception{
        //GIVEN - дано
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 10, 0, 0);
        ShortUserDto oowner = new ShortUserDto(1, "veter");
        LangCodeDto langCode = new LangCodeDto(1, "java", "java");
        PostDto postDto = new PostDto(1, "DEMO", "title 1", "description 1",
                date, date, oowner, langCode, 0);

        InputPostDto inputPostDto = new InputPostDto("DEMO", "title 1", "description 1", 1, 1);
        //WHEN - совершаем действие
        mvc.perform(patch("/post/1")
                        .header("Authorization", getBasicAuthenticationHeader())
                        .content(mapper.writeValueAsString(inputPostDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(postDto.getId()), Integer.class))
                .andExpect(jsonPath("$.code", is(postDto.getCode())))
                .andExpect(jsonPath("$.title", is(postDto.getTitle())))
                .andExpect(jsonPath("$.description", is(postDto.getDescription())))
                .andExpect(jsonPath("$.owner.id", is(postDto.getOwner().getId()), Integer.class))
                .andExpect(jsonPath("$.owner.login", is(postDto.getOwner().getLogin())))
                .andExpect(jsonPath("$.langCode.id", is(postDto.getLangCode().getId())))
                .andExpect(jsonPath("$.langCode.name", is(postDto.getLangCode().getName())));
    }
    @DisplayName("GIVEN ид для удаления поста" +
            " WHEN удаляем пост " +
            " THEN пост удален")
    @Test
    void shouldDeleteUser() throws Exception{
        //GIVEN - дано

        //WHEN - совершаем действие
        mvc.perform(delete("/user/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk());
    }

    @DisplayName("GIVEN данные для поиска страницы постов" +
            " WHEN ищем пост по коду языка, размеру страницы и начальной странице" +
            " THEN возвращаем данные страницы постов")
    @Test
    void shouldReturnPagePosts() throws Exception {
        //GIVEN - дано
        PostForListDto postForListDto3 = new PostForListDto(3, "title 3",null,"veter",1,0.0);
        PostForListDto postForListDto2 = new PostForListDto(2, "title 2",null,"veter",1,0.0);
        List<PostForListDto> content = List.of(postForListDto3, postForListDto2);
        PageDto<PostForListDto> pageDto = new PageDto<>(5, content, 1, 2, 3);


        //WHEN - совершаем действие
        mvc.perform(get("/post?from=1&size=2&id-lang-code=1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(pageDto.getTotal()), Long.class))
                .andExpect(jsonPath("$.pageNumber", is(pageDto.getPageNumber()), Integer.class))
                .andExpect(jsonPath("$.pageSize", is(pageDto.getPageSize()), Integer.class))
                .andExpect(jsonPath("$.totalPage", is(pageDto.getTotalPage()), Integer.class))
                .andExpect(jsonPath("$.content[1].title", is(postForListDto2.getTitle())))
                .andExpect(jsonPath("$.content[1].userLogin", is(postForListDto2.getUserLogin())))
                .andExpect(jsonPath("$.content[1].userId", is(postForListDto2.getUserId()), Integer.class));
    }
    private String getBasicAuthenticationHeader() {
        String username = "veter";
        String password = "123";
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
