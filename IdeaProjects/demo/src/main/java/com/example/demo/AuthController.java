package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Регистрация нового пользователя
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        // Проверка на существующего пользователя с таким же именем
        if (userRepository.findByUsername(username) != null) {
            return "Username already taken!";
        }

        // Создание нового пользователя
        User newUser = new User(username, password);  // Используем конструктор с параметрами
        userRepository.save(newUser);
        return "Registration successful!";
    }

    // Получение списка всех пользователей
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // Получение пользователя по имени пользователя
    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
