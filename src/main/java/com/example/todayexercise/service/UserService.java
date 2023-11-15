package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public String signup(SingUp signup) {
        User user = User.builder()
                .email(signup.getEmail())
                .password(signup.getPassword())
                .createdAt(LocalDateTime.now())
                .nickName(signup.getNickName())
                .isDeleted(false)
                .build();

        userRepository.save(user);

        return "회원가입 완료";
    }
}
