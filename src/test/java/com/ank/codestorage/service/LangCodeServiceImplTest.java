package com.ank.codestorage.service;

import com.ank.codestorage.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:test.properties")
public class LangCodeServiceImplTest {
    private final LangCodeService langCodeService;
    @Autowired
    private MockMvc mvc;

    @DisplayName("GIVEN ид не существующего языка" +
            " WHEN ищем язык" +
            " THEN Ошибка")
    @Test
    void shouldExceptionWhenFindLangCode() throws Exception {
        //GIVEN - дано
        int id = 3;
        //WHEN - совершаем действие
        NotFoundException exp = assertThrows(NotFoundException.class,
                ()->langCodeService.findById(id));
        //THEN - проверки
        assertFalse(exp.getMessage().isEmpty());
    }
}
