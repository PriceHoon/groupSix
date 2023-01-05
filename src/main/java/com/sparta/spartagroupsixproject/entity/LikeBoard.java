package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LikeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public LikeBoard(User user, Board board,boolean isCheck) {
        this.user = user;
        this.board = board;
        this.isCheck = isCheck;
    }


    public void update(boolean isCheck){
        this.isCheck = isCheck;
    }
}

