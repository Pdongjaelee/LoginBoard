package com.example.callbus.Member.Realtor.controller;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Grobal.security.UserDetailsImpl;
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
public class RealtorBoardController {
    private final RealtorBoardService realtorBoardService;


    //글 작성
    @PostMapping("/board")
    public ResponseDto<?> create(@RequestPart(value = "boardRequestDto") BoardRequestDto boardRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        return realtorBoardService.create(boardRequestDto, userDetailsImpl.getMember().getEmail());
    }
    //글 목록
    @GetMapping("/board")
    public ResponseDto<?> show() {
        return realtorBoardService.show();
    }

    //글 수정
    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return realtorBoardService.update(id, boardRequestDto, userDetailsImpl.getMember().getEmail());
    }

    //글 삭제
    @DeleteMapping("/board{id}")
    public ResponseDto<?> Delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return realtorBoardService.delete(id, userDetailsImpl.getMember().getEmail());
    }
}

