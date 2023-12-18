package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.LoginDTO;
import com.example.todayexercise.dto.request.SingUpDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;


    @Transactional
    public String signup(SingUpDTO signup) {
        if(userRepository.existsByEmail(signup.getEmail())) throw new UserException(UserErrorCode.EXIST_EMAIL);
        if(userRepository.existsByNickName(signup.getNickName())) throw new UserException(UserErrorCode.EXIST_NICKNAME);

        User user = User.builder()
                .email(signup.getEmail())
                .password(passwordEncoder.encode(signup.getPassword()))
                .createdAt(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
                .nickName(signup.getNickName())
                .isDeleted(false)
                .build();
        userRepository.save(user);
        return "회원가입 완료";
    }

    @Transactional(readOnly = true)
    public String login(LoginDTO loginDTO, HttpSession session) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if(user == null || !user.isPasswordMatch(passwordEncoder, loginDTO.getPassword()))
            throw new UserException(UserErrorCode.FAIL_TO_LOGIN);
        session.setAttribute("user",user);
        return "로그인 완료";
    }

    public String logout(HttpSession session,User user) {
        if(user == null) throw new UserException(UserErrorCode.NOT_EXIST_USER);
        log.info("로그아웃된 사용자 이메일 : {}", user.getEmail());
        session.invalidate();
        return "로그아웃 되었습니다.";
    }



    @Transactional
    public String updateImage(User user, MultipartFile imageFIle ) {
        Optional.ofNullable(imageFIle)
                .filter(file -> !file.isEmpty())
                .ifPresent(file -> {
                    if (user.getProfileImage() != null) s3Service.deleteImageFile(user.getProfileImage());
                    user.setProfileImage(s3Service.uploadImageFile(imageFIle));
                    userRepository.save(user);
                });
        return user.getProfileImage();
    }


    public String checkEmail(String email) {
        if(userRepository.existsByEmail(email))
           throw new UserException(UserErrorCode.EXIST_EMAIL) ;
        return "해당 이메일은 사용 가능합니다.";
    }

    public String checkNickName(String nickName) {
        if(userRepository.existsByNickName(nickName))
            throw new UserException(UserErrorCode.EXIST_NICKNAME) ;
        return "해당 nickName은 사용 가능합니다.";
    }

    @Transactional
    public String updateNickName(User user, String nickName) {
        Optional.ofNullable(nickName)
                .ifPresent(user::setNickName);
        userRepository.save(user);
        return user.getNickName();
    }

    @Transactional
    public String updatePassword(User user, String oldPassword,String newPassword) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new UserException(UserErrorCode.INCORRECT_PASSWORD);
        }

        Optional.ofNullable(newPassword)
                .ifPresent(password -> user.setPassword(passwordEncoder.encode(newPassword)));

        userRepository.save(user);
        return "비밀번호 수정 완료";
    }

    public Map<String,String> getInfo(User user) {
        User updatedUser = userRepository.findByEmail(user.getEmail());
        Map<String,String> info = new HashMap<>(2);
        info.put("nickName",updatedUser.getNickName());
        info.put("profileImage",updatedUser.getProfileImage());
        return info;
    }

}
