package com.ank.codestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Для тестирования возможности подключения
 */
@Getter
@Setter
@AllArgsConstructor
public class TestConnectDto {
    private String message;
    private LocalDateTime date;
}
