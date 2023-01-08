package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
public class Board extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long likenum;

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void updateLikeNum(Long likenum) {
        this.likenum = likenum;
    }

    public boolean isWriter(Long userId) {
        if (this.getUserId().equals(userId)) {
            return true;
        }
        return false;
    }


}
