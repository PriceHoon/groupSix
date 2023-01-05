package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board  extends TimeStamped {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String content;

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
        this.content =requestDto.getContent();
        this.user = user;
        this.likenum = 0L;
    }
    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content =requestDto.getContent();
    }

    public void updateLikeNum(Long likenum){
        this.likenum = likenum;
    }

    public boolean isWriter(Long userId){
        return this.user.isValidUserId(userId) || this.user.isAdmin();
    }


}
