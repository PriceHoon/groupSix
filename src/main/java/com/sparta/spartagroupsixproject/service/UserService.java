package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.entity.UserRoleEnum;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.CommentRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final BoardService boardService;
    private final CommentService commentService;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }

        // 사용자 ROLE 확인
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            userRoleEnum = UserRoleEnum.ADMIN;
        }

        User user = User.builder().username(username).password(password).userRoleEnum(userRoleEnum).build();
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getUserRoleEnum()));
        return "로그인이 완료 되었습니다";
    }

    @Transactional
    public String  delete(User user) {

        String userName = user.getUsername();
        //해당 유저에 관한 모든 정보를 지울 때 여기서 다 호출해서 쓰는게 맞을까 의문.
        userRepository.deleteById(user.getId());

        boardService.deleteAllBoardByUser(user.getId());
        commentService.deleteCommentByUser(user.getId());


//        List<Comment> commentList = commentRepository.findAllByUserId(user.getId())


        return userName+"님이 탈퇴 되셨습니다, 관련 정보는 모두 사라집니다!";

    }
}
