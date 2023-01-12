package com.sparta.spartagroupsixproject.service;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import com.sparta.spartagroupsixproject.dto.BoardResponseDto;
import com.sparta.spartagroupsixproject.dto.PageDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor


public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시글 전체 조회
    @Transactional
    public Page<BoardResponseDto> getBoardAll(PageDto pageDto) {

        Sort.Direction direction = pageDto.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPage() - 1, pageDto.getSize(), sort);

        Page<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc(pageable);

        return new PageImpl<>(boards.stream().map(BoardResponseDto::new).collect(Collectors.toList()));

//        for (Board board : boards) {
//            User user = userRepository.findById(board.getUserId()).orElse(User.builder().username("삭제된 사용자입니다").build());
//            boardResponseDtoList.add(new BoardResponseDto(board,user.getUsername()));
//        }

    }

    //게시글 생성
    @Transactional
    public String createBoard(BoardRequestDto requestDto, User user) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = Board.builder().title(requestDto.getTitle()).content(requestDto.getContent()).userId(user.getId()).likenum(0L).build();
        boardRepository.save(board);
        return "게시글 생성완료";
    }


    // 게시글 선택 조회
    @Transactional
    public BoardResponseDto findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다"));
        String username = userRepository.findById(board.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다")).getUsername();
        return new BoardResponseDto(board, username);
    }

    // 게시물 수정
    @Transactional
    public String update(Long id, BoardRequestDto requestDto, User user) {

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );

        if (board.isWriter(user.getId())|| user.isAdmin()) {
            board.update(requestDto);

            return "해당 게시물이 수정되었습니다";
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 수정할 수 없습니다!");
        }
    }


    @Transactional
    public String delete(Long id, User user) {
        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하고자 하는 게시글이 없습니다!")
        );

        if (board.isWriter(user.getId())|| user.isAdmin()) {
            boardRepository.delete(board);
            return "게시글 삭제 성공.";
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");

        }

    }

    @Transactional
    public void deleteAllBoardByUser(Long userId){
        boardRepository.deleteAllByUserId(userId);
    }


}




