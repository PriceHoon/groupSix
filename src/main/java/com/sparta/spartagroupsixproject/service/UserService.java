package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.entity.UserRoleEnum;
import com.sparta.spartagroupsixproject.jwt.Filter;
import com.sparta.spartagroupsixproject.jwt.JwtEnum;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final BoardService boardService;
    private final CommentService commentService;

    private final Filter filter;

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
        response.addHeader(JwtUtil.REFRESH_HEADER, jwtUtil.createRefreshToken(user.getUsername(), JwtEnum.Refresh));
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

    public String getAccessToken(HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException {
        if(jwtUtil.checkRefreshToken(request)) {
            String username = filter.checkUser(request);
            User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지않는 유저입니다"));
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getUserRoleEnum()));
            return "Access 토큰이 재생성 되었습니다.";
        }
        throw new IllegalArgumentException("해당 토큰은 기한이 지난 토큰입니다");
    }
}
