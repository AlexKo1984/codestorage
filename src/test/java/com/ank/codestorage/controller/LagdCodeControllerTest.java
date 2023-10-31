package com.ank.codestorage.controller;

import com.ank.codestorage.dto.CreateUserDto;
import com.ank.codestorage.dto.LangCodeDto;
import com.ank.codestorage.dto.PostDto;
import com.ank.codestorage.dto.ShortUserDto;
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
public class LagdCodeControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @DisplayName("GIVEN хотим получить все языки" +
            " WHEN получаем все языки" +
            " THEN возвращаем данные всех языков")
    @Test
    void shouldReturnAllLangCode() throws Exception {
        //GIVEN - дано
        //Создан data-test.sql
        LangCodeDto langCodeDto1 = new LangCodeDto(1, "java", "Java");
        LangCodeDto langCodeDto2 = new LangCodeDto(2, "python", "Python");

        //WHEN - совершаем действие
        mvc.perform(get("/lang_code")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //THEN - проверки
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(langCodeDto1.getId()), Integer.class))
                .andExpect(jsonPath("$.[0].name", is(langCodeDto1.getName())))
                .andExpect(jsonPath("$.[0].title", is(langCodeDto1.getTitle())))
                .andExpect(jsonPath("$.[1].id", is(langCodeDto2.getId()), Integer.class))
                .andExpect(jsonPath("$.[1].name", is(langCodeDto2.getName())))
                .andExpect(jsonPath("$.[1].title", is(langCodeDto2.getTitle())));
    }
}
