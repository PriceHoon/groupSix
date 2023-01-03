package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.repository.CommentRepository;
import com.sparta.spartagroupsixproject.repository.LikeCommentRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // 설정 문제, 이 선언을 해줘야 mock인식으로 run
class LikeCommentServiceTest {

    @Mock
    LikeCommentRepository likeCommentRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks  // 가짜 객체 삽입,
    LikeCommentService likeCommentService;

    @Test
    void clickFavorite() throws Exception {

        //given
        User user = mock(User.class);
        System.out.println(user.getUsername());
        Comment comment = mock(Comment.class);
        when(user.getUsername()).thenReturn("userA");
        when(comment.getId()).thenReturn(1L);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        when(likeCommentRepository.existsByUserAndComment(user, comment)).thenReturn(false);
        //when
        String s = likeCommentService.clickFavorite(comment.getId(), user.getUsername());

        //then
        Assertions.assertThat(s).isEqualTo("좋아요를 누르셨습니다");
    }

    @Test
    void cancelFavorite() {

    }
}
