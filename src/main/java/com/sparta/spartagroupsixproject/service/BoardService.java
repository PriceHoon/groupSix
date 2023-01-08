package com.sparta.spartagroupsixproject.service;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import com.sparta.spartagroupsixproject.dto.BoardResponseDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private final JwtUtil jwtUtil;

    //게시글 전체 조회
    @Transactional
    public List<BoardResponseDto> getBoardAll() {

        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
       // List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

//        for 문으로 다 넣어주기 보다는 Stream을 사용해서 한줄로 표현하는게 훨씬 깔끔한 것 같아 수정.
//        for (Board board : boards) {
//            boardResponseDtoList.add(new BoardResponseDto(board));
//        }

        List<BoardResponseDto> boardResponseDtoList = boards.stream().map(b -> new BoardResponseDto(b)).collect(Collectors.toList());
        return boardResponseDtoList;
    }

    //게시글 생성
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = new Board(requestDto, user);
        boardRepository.saveAndFlush(board);
        return new BoardResponseDto(board);
    }


    // 게시글 선택 조회
    @Transactional
    public BoardResponseDto findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다"));
        return new BoardResponseDto(board);
    }

    // 게시물 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, User user) {

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );

        if (board.isWriter(user.getId())) {
            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 수정할 수 없습니다!");
        }
    }


    @Transactional
    public ResponseEntity<String> delete(Long id, User user) {
        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하고자 하는 게시글이 없습니다!")
        );

        if (board.isWriter(user.getId())) {
            boardRepository.delete(board);
            return new ResponseEntity<>("게시글 삭제 성공.", HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");

        }

    }
}




