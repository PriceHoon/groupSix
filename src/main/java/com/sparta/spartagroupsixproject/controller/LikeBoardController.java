package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.jwt.Filter;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import com.sparta.spartagroupsixproject.service.LikeBoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeBoardController {

    private final LikeBoardService likeBoardService;

    private final UserRepository userRepository;

    private final Filter filter;

    @PostMapping("/board/{id}/like")
    public ResponseEntity changeLike(@PathVariable Long id, HttpServletRequest request) {

          String username = filter.checkUser(request);

         User user = userRepository.findByUsername(username).orElseThrow(
                 ()-> new IllegalArgumentException("해당 유저를 찾을 수 없습니다!")
         );
//        @AuthenticationPrincipal UserDetailsImpl userDetails

        //userDetails.getUser()
        return ResponseEntity.status(HttpStatus.OK).body(likeBoardService.changeLike(id,user));
    }

}
