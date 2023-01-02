package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "commentID")
    private Comment Comment;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public LikeComment(Comment comment, User user) {
        this.Comment = comment;
        this.user = user;
    }
}
