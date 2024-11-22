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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register").permitAll()  // Разрешаем доступ к этим страницам без аутентификации
                                .anyRequest().authenticated()  // Все остальные страницы требуют аутентификации
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home", false)
                                .permitAll()
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .permitAll()
                )
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint((request, response, authException) ->
                                response.sendRedirect("/login?error") // Обработка ошибок аутентификации
                        )
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Используем BCrypt для шифрования паролей
    }
}



