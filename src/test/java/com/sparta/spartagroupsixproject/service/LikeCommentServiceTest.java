package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.LikeComment;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.repository.CommentRepository;
import com.sparta.spartagroupsixproject.repository.LikeCommentRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.restdocs.RestDocumentationExtension;

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
    @DisplayName("좋아요 성공 케이스")
    void clickFavorite() throws Exception {

        //given
        User user = mock(User.class);
        Comment comment = mock(Comment.class);
//        when(user.getUsername()).thenReturn("userA");
        when(comment.getId()).thenReturn(1L);
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        when(likeCommentRepository.existsByUserIdAndCommentId(user.getId(), comment.getId())).thenReturn(false);

        //when
        String s = likeCommentService.clickFavorite(comment.getId(), user);

        //then
        Assertions.assertSame("좋아요를 누르셨습니다", s);
    }

    @DisplayName("좋아요 실패 케이스")
    @Test
    void clickFavoriteError() throws Exception {
        //given
        User user = mock(User.class);
        Comment comment = mock(Comment.class);
//        when(user.getUsername()).thenReturn("userA");
        when(comment.getId()).thenReturn(1L);
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        when(likeCommentRepository.existsByUserIdAndCommentId(user.getId(), comment.getId())).thenReturn(true);
        //when / then
        Assertions.assertThrows(Exception.class, () -> likeCommentService.clickFavorite(comment.getId(), user));
    }

//    @Test
//    @DisplayName("좋아요 취소 성공 케이스")
//    void cancelFavorite() throws Exception {
//        //given
//        User user = mock(User.class);
//        Comment comment = mock(Comment.class);
//        LikeComment likeComment = mock(LikeComment.class);
//        when(user.getUsername()).thenReturn("user1");
//        when(comment.getId()).thenReturn(1L);
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
//        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
//        when(likeCommentRepository.existsByUserAndComment(user, comment)).thenReturn(true);
//        //when
////        String s = likeCommentService.cancelFavorite(comment.getId(), user);
//        //then
//        Assertions.assertSame("좋아요를 취소하였습니다", s);
//    }

    @Test
    @DisplayName("좋아요 취소 실패 케이스")
    void cancelFavoriteException() throws Exception {
        //given
        User user = mock(User.class);
        Comment comment = mock(Comment.class);
        LikeComment likeComment = mock(LikeComment.class);
//        when(user.getUsername()).thenReturn("user1");
        when(comment.getId()).thenReturn(1L);
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
//        when(likeCommentRepository.existsByUserAndComment(user, comment)).thenReturn(false);
        //when //then
        Assertions.assertThrows(Exception.class, () -> likeCommentService.cancelFavorite(comment.getId(), user));

    }
}
