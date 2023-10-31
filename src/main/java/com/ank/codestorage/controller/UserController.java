package com.ank.codestorage.controller;

import com.ank.codestorage.dto.CreateUserDto;
import com.ank.codestorage.dto.PageDto;
import com.ank.codestorage.dto.UpdateUserDto;
import com.ank.codestorage.dto.UserDto;
import com.ank.codestorage.exception.NotValidException;
import com.ank.codestorage.mapper.UserMapper;
import com.ank.codestorage.model.User;
import com.ank.codestorage.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/pages")
    public PageDto<UserDto> findAll(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                           @Positive @RequestParam(defaultValue = "10") Integer size) {
        return userMapper.mapToPageUserDto(userService.findAll(from, size));
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
        return userMapper.mapToUserDto(userService.findById(id));
    }

    @PostMapping("")
    public UserDto create(@Valid @RequestBody CreateUserDto createUserDto) {
        User user = userService.create(userMapper.mapToUser(createUserDto));
        return userMapper.mapToUserDto(user);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Integer id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        User user = userService.update(id, userMapper.mapToUser(updateUserDto));
        return userMapper.mapToUserDto(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    /**
     * Для аутентификации из CodeView
     */
    @GetMapping("/au/{login}")
    public UserDto findByLogin(@PathVariable String login) {
        return userMapper.mapToUserDto(userService.findByLogin(login));
    }
}
