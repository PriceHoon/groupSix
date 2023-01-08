package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@Entity
//@Builder
@SuperBuilder
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private Long likenum;


    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long boardId;



    public boolean isWriter(Long userId){
        return this.userId.equals(userId);
    }
    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }
    public void updateLikeNum(Long likenum){
        this.likenum = likenum;
    }
//
//    public boolean isAuthenticatedUser(Long id) {
//
//        if(user.isValidUserId(id)){
//            return true;
//        }
//        return false;
//
//    }
}
