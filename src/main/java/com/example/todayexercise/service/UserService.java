package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.Login;
import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.exception.domain.User.UserErrorCode;
import com.example.todayexercise.exception.domain.User.UserException;
import com.example.todayexercise.repository.User.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signup(SingUp signup) {
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
            throw new UserException(UserErrorCode.FAIL_TO_LOGIN);
        session.setAttribute("user",user);
        return session.getId();
    }

    public String logout(HttpSession session,User user) {
        if(user == null) throw new UserException(UserErrorCode.NOT_EXIST_USER);
        log.info("로그아웃된 사용자 이메일 : {}", user.getEmail());
        session.invalidate();
        return "로그아웃 되었습니다.";
    }
}
