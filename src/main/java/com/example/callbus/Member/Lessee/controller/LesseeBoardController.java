package com.example.callbus.Member.Lessee.controller;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Grobal.security.UserDetailsImpl;
import com.example.callbus.Member.Lessee.service.LesseeBoardService;
import com.example.callbus.Member.Lessor.service.LessorBoardService;
import com.example.callbus.Member.dto.request.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class LesseeBoardController {
    private final LesseeBoardService lesseeBoardService;


    //글 작성
    @PostMapping("/board")
    public ResponseDto<?> boardCreate(@RequestPart(value = "boardRequestDto") BoardRequestDto boardRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        return lesseeBoardService.boardCreate(boardRequestDto, userDetailsImpl.getMember().getEmail());
    }
    //글 목록
    @GetMapping("/board")
    public ResponseDto<?> show() {
        return lesseeBoardService.show();
    }

    //글 수정
    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return lesseeBoardService.update(id, boardRequestDto, userDetailsImpl.getMember().getEmail());
    }

    //글 삭제
    @DeleteMapping("/board{id}")
    public ResponseDto<?> Delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return lesseeBoardService.delete(id, userDetailsImpl.getMember().getEmail());
    }
}

