package com.example.callbus.Member.Realtor.service;

import com.example.callbus.Grobal.dto.ResponseDto;
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
public class RealtorBoardService {

    private final RealtorRepository realtorRepository;
    private final BoardRepository boardRepository;

    // username을 이용해서 User 객체 만들기 및 유저정보 확인 ( user 사용해서)
    private Realtor getRealtor(String email) {
        Realtor realtor = realtorRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException() );
        return realtor;
    }

    //글 작성
    @Transactional
    public ResponseDto<?> create(BoardRequestDto boardRequestDto, String email) throws IOException { {
        Realtor realtor = getRealtor(email);
        Board board = new Board(boardRequestDto, realtor);
        boardRepository.save(board);

        return ResponseDto.success(
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .email(board.getRealtor().getEmail())
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
        Realtor realtor = getRealtor(email);
        Board  board = boardRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());
        if (!realtor.getEmail().equals(board.getRealtor().getEmail())) {
            throw new RuntimeException();
        }
        boardRepository.deleteById(id);
        return ResponseDto.success("delete success");
    }
    //글 수정
    @Transactional
    public ResponseDto<?> update (Long id, BoardRequestDto boardRequestDto, String email) {
        Realtor member = getRealtor(email);
        Board  board = boardRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());

        if (!member.getEmail().equals(board.getRealtor().getEmail())) {
            throw new RuntimeException();
        }

        board.update(boardRequestDto);
        boardRepository.save(board);
        return ResponseDto.success(board);
    }

}
