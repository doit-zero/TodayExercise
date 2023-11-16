package com.example.todayexercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Builder

    public User(String email, String password, String nickName, LocalDateTime createdAt, Boolean isDeleted) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public Boolean isPasswordMatch(PasswordEncoder passwordEncoder,String password) {
        return passwordEncoder.matches(password,this.password);
    }

    public User() {

    }
}
