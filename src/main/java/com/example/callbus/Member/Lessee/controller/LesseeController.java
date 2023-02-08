package com.example.callbus.Member.Lessee.controller;

import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Member.dto.request.MemberRequestDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.Lessee.service.LesseeService;
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
public class LesseeController {

    private final LesseeService lesseeService;


    //회원가입
    @PostMapping("/signup")
    public ResponseDto<String> LesseeSignup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return lesseeService.LesseeSignup(memberRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> LesseeLogin(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                               HttpServletResponse response) {
        return lesseeService.LesseeLogin(loginRequestDto, response);
    }
}

