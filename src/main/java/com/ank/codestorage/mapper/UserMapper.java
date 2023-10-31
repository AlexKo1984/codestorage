package com.ank.codestorage.mapper;

import com.ank.codestorage.dto.*;
import com.ank.codestorage.model.User;
import com.ank.codestorage.model.enums.TypeUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public List<UserDto> mapToUserDto(Iterable<User> users) {
        List<UserDto> result = new ArrayList<>();

        for (User user : users) {
            result.add(mapToUserDto(user));
        }

        return result;
    }
    public PageDto<UserDto> mapToPageUserDto(Page<User> page) {
        List<UserDto> content = new ArrayList<>();

        for (User user : page.getContent()) {
            content.add(mapToUserDto(user));
        }

        return new PageDto<>(page.getTotalElements(), content, page.getNumber(), page.getSize(), page.getTotalPages());
    }
    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getEmail(), String.valueOf(user.getTypeUser()));
    }
    public CreateUserDto mapToCreateUserDto(User user) {
        return new CreateUserDto(user.getName(), user.getLogin(), user.getEmail(), user.getPassword(), String.valueOf(user.getTypeUser()));
    }
    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        return user;
    }

    public User mapToUser(CreateUserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setTypeUser(TypeUser.valueOf(userDto.getTypeUser()));
        return user;
    }
    public User mapToUser(UpdateUserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return user;
    }

    public ShortUserDto mapToShortUserDto(User user) {
        return new ShortUserDto(user.getId(), user.getLogin());
    }
}
