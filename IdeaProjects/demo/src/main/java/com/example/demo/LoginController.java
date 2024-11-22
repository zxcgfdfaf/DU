package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // возвращает страницу login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // возвращает страницу register.html
    }
}
