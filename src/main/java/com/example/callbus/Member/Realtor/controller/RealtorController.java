package com.example.callbus.Member.Realtor.controller;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.Realtor.service.RealtorService;
import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Member.dto.request.MemberRequestDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
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
public class RealtorController {

    private final RealtorService realtorService;


    //회원가입
    @PostMapping("/signup")
    public ResponseDto<String> RealtorSignup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return realtorService.RealtorSignup(memberRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> RealtorLogin(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                               HttpServletResponse response) {
        return realtorService.RealtorLogin(loginRequestDto, response);
    }
}
