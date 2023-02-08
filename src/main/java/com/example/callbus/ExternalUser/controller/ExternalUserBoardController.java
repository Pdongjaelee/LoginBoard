package com.example.callbus.ExternalUser.controller;

import com.example.callbus.ExternalUser.service.ExternalUserBoardService;
import com.example.callbus.Grobal.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class ExternalUserBoardController {
    private final ExternalUserBoardService externalUserBoardService;

    //글 목록
    @GetMapping("/board")
    public ResponseDto<?> externalShow() {
        return externalUserBoardService.externalShow();
    }
}

