package com.example.spring_web_security.controller;

import com.example.spring_web_security.repository.UserRepository;
import com.example.spring_web_security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User RegisterUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("login")
    public User LoginUser(@RequestBody User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
