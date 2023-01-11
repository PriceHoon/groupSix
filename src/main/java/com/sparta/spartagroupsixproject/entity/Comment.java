package com.sparta.spartagroupsixproject.entity;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    


    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Comment> child = new ArrayList<>();


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
