package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
//@NoArgsConstructor
//@SuperBuilder
@Builder
@AllArgsConstructor
public class LikeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private boolean isCheck;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long boardId;

    public LikeBoard() {

    }


    public void update(boolean isCheck){
        this.isCheck = isCheck;
    }
}

