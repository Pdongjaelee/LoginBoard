package com.example.callbus.Member.Lessor.controller;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Grobal.security.UserDetailsImpl;
import com.example.callbus.Member.Lessor.service.LessorBoardService;
import com.example.callbus.Member.Lessor.service.LessorLikeService;
import com.example.callbus.Member.Lessor.service.LessorService;
import com.example.callbus.Member.Realtor.service.RealtorBoardService;
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
public class LessorBoardController {
    private final LessorBoardService lessorBoardService;


    //글 작성
    @PostMapping("/board")
    public ResponseDto<?> boardCreate(@RequestPart(value = "boardRequestDto") BoardRequestDto boardRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        return lessorBoardService.boardCreate(boardRequestDto, userDetailsImpl.getMember().getEmail());
    }
    //글 목록
    @GetMapping("/board")
    public ResponseDto<?> show() {
        return lessorBoardService.show();
    }

    //글 수정
    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return lessorBoardService.update(id, boardRequestDto, userDetailsImpl.getMember().getEmail());
    }

    //글 삭제
    @DeleteMapping("/board{id}")
    public ResponseDto<?> Delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return lessorBoardService.delete(id, userDetailsImpl.getMember().getEmail());
    }
}

