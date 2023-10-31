package com.ank.codestorage.controller;

import com.ank.codestorage.dto.TestConnectDto;
import com.ank.codestorage.mapper.UserMapper;
import com.ank.codestorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping()
    public TestConnectDto test(){
//        http://localhost:8080/test
        return new TestConnectDto("ok", LocalDateTime.now());
    }
}
