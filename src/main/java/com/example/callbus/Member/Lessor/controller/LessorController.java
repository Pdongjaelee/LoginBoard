package com.example.callbus.Member.Lessor.controller;

import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Member.dto.request.MemberRequestDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.Lessor.service.LessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LessorController {

    private final LessorService lessorService;


    //회원가입
    @PostMapping("/signup")
    public ResponseDto<String> LessorSignup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return lessorService.LessorSignup(memberRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> LessorLogin(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                               HttpServletResponse response) {
        return lessorService.LessorLogin(loginRequestDto, response);
    }
}

