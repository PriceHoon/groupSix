package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID",nullable = false)
    private Board board;


    //각 역할에 맞는 Dto는 이름만 맞춰서 만들어 작업해주세요!
    public Comment(CommentRequestDto requestDto, User user, Board board) {
        this.content = requestDto.getContent;
        this.user = user;
        this.board = board;
    }

    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
