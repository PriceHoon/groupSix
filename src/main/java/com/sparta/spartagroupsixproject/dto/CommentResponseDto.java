package com.sparta.spartagroupsixproject.dto;

import com.sparta.spartagroupsixproject.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likenum;

    public CommentResponseDto(Comment  comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.likenum = comment.getLikenum();
        this.createdAt =comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
