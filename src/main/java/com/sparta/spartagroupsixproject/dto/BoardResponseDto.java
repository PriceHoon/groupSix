package com.sparta.spartagroupsixproject.dto;
import com.sparta.spartagroupsixproject.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;



@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private String title;

    private String username;

    private String content;
    private LocalDateTime createdAt;

    private Long likenum;
    private LocalDateTime modifiedAt;
//    private List<CommentResponseDto> commentList = new ArrayList<>();

    public BoardResponseDto(Board board ) {
        this.title = board.getTitle();
        this.username = board.getUser().getUsername();
        this.content = board.getContent();
        this.likenum = board.getLikenum();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}


