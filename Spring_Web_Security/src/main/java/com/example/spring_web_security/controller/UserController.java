package com.example.spring_web_security.controller;

import com.example.spring_web_security.dto.AuthRequestDto;
import com.example.spring_web_security.dto.AuthResponse;
import com.example.spring_web_security.jwtutil.JwtUtil;
import com.example.spring_web_security.repository.UserRepository;
import com.example.spring_web_security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("test1")
    public String test() {
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saved = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(token, saved.getName(), saved.getEmail()));
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
//        if (userRepository.findByEmail(user.getEmail()) != null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Hash password in production!
//        User saved = userRepository.save(user);
//        String token = jwtUtil.generateToken(user.getEmail());
//
//        return ResponseEntity.ok(new AuthResponse(token, saved.getName(), saved.getEmail()));
//    }

//    @PostMapping("/registers")
//    public ResponseEntity<String> registers(@RequestBody User user) {
//        System.out.println("Registering user: " + user);
//        userRepository.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    }


//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequestDto request) {
//        User user = userRepository.findByEmail(request.getEmail());
//
//        if (user == null || !user.getPassword().equals(request.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail());
//        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(user.getEmail());
        System.out.printf("Login attempt: %s\n", request.getEmail());
        System.out.println("Password: " + request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));

    }
}