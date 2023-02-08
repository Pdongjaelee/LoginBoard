package com.example.callbus.Member.Lessor.service;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.Lessor.entity.Lessor;
import com.example.callbus.Member.Lessor.repository.LessorRepository;
import com.example.callbus.Member.Realtor.entity.Realtor;
import com.example.callbus.Member.Realtor.repository.RealtorRepository;
import com.example.callbus.Member.dto.request.BoardRequestDto;
import com.example.callbus.Member.dto.response.BoardResponseDto;
import com.example.callbus.Member.entity.Board;
import com.example.callbus.Member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@RequiredArgsConstructor
@Service
public class LessorBoardService {

    private final LessorRepository lessorRepository;
    private final BoardRepository boardRepository;

    // username을 이용해서 User 객체 만들기 및 유저정보 확인 ( user 사용해서)
    private Lessor getLessor(String email) {
        Lessor lessor = lessorRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException() );
        return lessor;
    }

    //글 작성
    @Transactional
    public ResponseDto<?> boardCreate(BoardRequestDto boardRequestDto, String email) throws IOException { {
        Lessor lessor = getLessor(email);
        Board board = new Board(boardRequestDto, lessor);
        boardRepository.save(board);

        return ResponseDto.success(
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .email(board.getLessor().getEmail())
                        .createAt(board.getCreatedAt())
                        .modifiedAt(board.getModifiedAt())
                        .build()
        );
    }
    }
    //글 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> show() {
        return ResponseDto.success(boardRepository.findAllByOrderByModifiedAtDesc());
        //글 작성자 타입 표시(한글)
        //글 목록 중 자신의 좋아요 확인 가능
        //글에 달린 좋아요 수 표시
    }


    //글 삭제
    @Transactional
    public ResponseDto<?> delete(Long id, String email) {
        Lessor lessor = getLessor(email);
        Board  board = boardRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());
        if (!lessor.getEmail().equals(board.getLessor().getEmail())) {
            throw new RuntimeException();
        }
        boardRepository.deleteById(id);
        return ResponseDto.success("delete success");
    }
    //글 수정
    @Transactional
    public ResponseDto<?> update (Long id, BoardRequestDto boardRequestDto, String email) {
        Lessor lessor = getLessor(email);
        Board  board = boardRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());

        if (!lessor.getEmail().equals(board.getLessor().getEmail())) {
            throw new RuntimeException();
        }

        board.update(boardRequestDto);
        boardRepository.save(board);
        return ResponseDto.success(board);
    }

}
