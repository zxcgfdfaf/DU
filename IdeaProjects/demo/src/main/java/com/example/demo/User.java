package com.example.demo;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private Long id;
    private String username;
    private String password;

    // Конструктор по умолчанию
    public User() {
    }

    // Конструктор с параметрами
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters и Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
