package com.ank.codestorage.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UserDtoTest {
    private final JacksonTester<UserDto> json;
    private UserDto userDto;
    private final Validator validator;

    UserDtoTest(@Autowired JacksonTester<UserDto> json) {
        this.json = json;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1, "Bob B", "bob", "bob@q.ru", "USER");
    }

    @DisplayName("Конвертация userDto в json")
    @Test
    void testJsonUserDto() throws Exception {
        JsonContent<UserDto> result = json.write(userDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Bob B");
        assertThat(result).extractingJsonPathStringValue("$.login").isEqualTo("bob");
        assertThat(result).extractingJsonPathValue("$.email").isEqualTo("bob@q.ru");
    }
}