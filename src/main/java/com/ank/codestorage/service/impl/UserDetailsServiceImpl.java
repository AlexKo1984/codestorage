package com.ank.codestorage.service.impl;

import com.ank.codestorage.model.User;
import com.ank.codestorage.model.enums.TypeUser;
import com.ank.codestorage.repositorie.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Для авторизации

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin(username).
                orElseThrow(() -> new UsernameNotFoundException(username));

        String[] authorities = user.getTypeUser() == TypeUser.USER ? new String[]{"USER"} : new String[]{"ADMIN", "USER"};

        UserDetails userDetails = org.springframework.security.core.userdetails
                .User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities(authorities)
                .build();

        return userDetails;
    }
}
