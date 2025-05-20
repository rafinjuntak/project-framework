package com.example.spring_web_security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
//@Table(name = "user")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;

    private String role = "USER";
}