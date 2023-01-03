package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    //댓글 관련 연관관계 아직 신경 안쓰셔도 됩니다!
//    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
//    private List<LikeBoard> likeBoards = new ArrayList<>();

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
