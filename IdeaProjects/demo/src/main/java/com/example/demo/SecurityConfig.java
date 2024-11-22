package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Используем BCrypt для шифрования паролей
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register").permitAll()  // Разрешаем доступ к этим страницам без аутентификации
                                .anyRequest().authenticated()  // Все остальные страницы требуют аутентификации
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Страница для входа
                                .permitAll()  // Разрешаем всем посещение страницы входа без аутентификации
                                .defaultSuccessUrl("/home", true)  // URL, на который перенаправляется после успешной аутентификации
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new org.springframework.security.web.util.matcher.AntPathRequestMatcher("/logout"))  // Настройка URL-адреса для выхода
                                .permitAll()  // Разрешаем всем выходить без аутентификации
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) ->
                                        response.sendRedirect("/login?error")  // URL, на который отправляется пользователь в случае аутентификации
                                )
                );
        return http.build();
    }
}
