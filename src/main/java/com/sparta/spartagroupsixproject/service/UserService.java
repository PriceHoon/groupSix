package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.entity.UserRoleEnum;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
//    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "";

    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            userRoleEnum = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, userRoleEnum);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (password.equals(user.getPassword())) {
            String generatedToken = jwtUtil.createToken(user.getUsername(), user.getUserRoleEnum());

            return generatedToken;
        } else {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }
//        if(!passwordEncoder.matches(password, user.getPassword())){
//            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }


    }
}