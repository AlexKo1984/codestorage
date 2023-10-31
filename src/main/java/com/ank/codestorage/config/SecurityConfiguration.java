package com.ank.codestorage.config;

import com.ank.codestorage.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests((authz) -> authz
                                .requestMatchers(new AntPathRequestMatcher("/user{id}", "DELETE")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/user{id}", "PATCH")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/post", "POST")).hasAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/post/{id}", "PATCH")).hasAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/post/{id}", "DELETE")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/comment", "POST")).hasAuthority("USER")
                                .requestMatchers(new AntPathRequestMatcher("/comment/{id}", "DELETE")).hasAuthority("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/grade-post", "POST")).hasAuthority("USER")
                                //.requestMatchers(antMatcher(HttpMethod.POST)
                                .anyRequest().permitAll()
                        )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsServiceImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public AuthenticationManager authManager() {
//        var authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(authProvider);
//    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}