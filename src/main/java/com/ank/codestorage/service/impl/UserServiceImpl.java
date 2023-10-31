package com.ank.codestorage.service.impl;

import com.ank.codestorage.exception.UserAlreadyExistException;
import com.ank.codestorage.exception.NotFoundException;
import com.ank.codestorage.mapper.UserMapper;
import com.ank.codestorage.model.User;
import com.ank.codestorage.model.enums.TypeUser;
import com.ank.codestorage.repositorie.UserRepository;
import com.ank.codestorage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

//D:\java\Spring\Spring_Hibernate\SpringMVC\src\main\java\org\AlexKo\SpringMVC

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Page<User> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        Page<User> page =  userRepository.findAll(pageable);

        return page;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
    }

    @Transactional
    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        checkEmail(user.getEmail(), user.getId());
        checkLogin(user.getLogin());
        log.info("Создание пользователя с ид: " + user.getId());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(Integer id, User userForUpdate) {
        checkEmail(userForUpdate.getEmail(), id);

        User user = userRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));

        user.setName(userForUpdate.getName());
        user.setEmail(userForUpdate.getEmail());

        log.info("Обновление пользователя с ид: " + id);

        return userRepository.save(user);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        userRepository.delete(user);
        log.info("Удален пользователь с ид: " + id);
    }

    private NotFoundException throwNotFoundException(Integer id) {
        String message = "Пользователь с id " + id + " не найден!.";
        log.warn(message);
        return new NotFoundException(message);
    }
    private void checkEmail(String email, Integer id) {
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            String message = "Пользователь с электронной почтой " + email + " уже зарегистрирован.";
            log.warn(message);
            throw new UserAlreadyExistException(message);
        }
    }
    private void checkLogin(String login) {
        if (userRepository.existsByLogin(login)) {
            String message = "Пользователь с логином " + login + " уже зарегистрирован.";
            log.warn(message);
            throw new UserAlreadyExistException(message);
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login).
                orElseThrow(() -> throwUsernameNotFoundException(login));

        //Если не админ, то нельзя получать чужие данные
        if (user.getTypeUser() != TypeUser.ADMIN && !login.equals(user.getLogin())){
            throw throwUsernameNotFoundException(login);
        }

        return user;
    }

    private UsernameNotFoundException throwUsernameNotFoundException(String login) {
        String message = "Пользователь: '" + login + "' не найден!";
        log.warn(message);
        return new UsernameNotFoundException(message);
    }
}
