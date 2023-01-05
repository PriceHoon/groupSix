package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID",nullable = false)
    private Board board;



    public Comment (CommentRequestDto requestDto, User user, Board board){
        this.content = requestDto.getContent();
        this.board = board;
        this.user = user;
    }

    public boolean isWriter(Long userId){
        return this.user.isValidUserId(userId) || this.user.isAdmin();
    }
    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }

    public boolean isAuthenticatedUser(Long id) {

        if(user.isVaildUser(id)){
            return true;
        }
        return false;

    }
}
