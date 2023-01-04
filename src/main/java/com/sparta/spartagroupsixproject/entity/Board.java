package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity

public class Board  extends TimeStamped {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column
    private Long likenum;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<LikeBoard> likeBoards = new ArrayList<>();

    public Board (BoardRequestDto requestDto, User user){
        this.title = requestDto.getTitle();
        this.contents =requestDto.getContents();
        this.user = user;
        this.likenum = 0L;
    }
    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents =requestDto.getContents();
    }

    public void updateLikeNum(Long likenum){
        this.likenum = likenum;
    }


    //각 역할에 맞는 Dto는 이름만 맞춰서 만들어 작업해주세요!
//    public Board(BoardRequestDTO boardDto , User user) {
//        this.title = boardDto.getTitle();
//        this.contents = boardDto.getContents();
//        this.user = user;
//    }

   // public void update(BoardRequestDTO boardDto){
//        this.title = boardDto.getTitle();
//        this.contents = boardDto.getContents();
//    }


}
