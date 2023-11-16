package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.Login;
import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.exception.domain.CommonErrorCode;
import com.example.todayexercise.exception.domain.CommonException;
import com.example.todayexercise.repository.User.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(SingUp signup) {
        // TODO 이메일 & 닉네임 중복 검사

        User user = User.builder()
                .email(signup.getEmail())
                .password(passwordEncoder.encode(signup.getPassword()))
                .createdAt(LocalDateTime.now())
                .nickName(signup.getNickName())
                .isDeleted(false)
                .build();

        userRepository.save(user);
        return "회원가입 완료";
    }


    @Transactional(readOnly = true)
    public String login(Login login, HttpSession session) {
        User user = userRepository.findByEmail(login.getEmail());
        if(user == null || !user.isPasswordMatch(passwordEncoder,login.getPassword()))
            throw new CommonException(CommonErrorCode.FAIL_TO_SAVE);
        session.setAttribute("user",user);
        return session.getId();
    }
}
