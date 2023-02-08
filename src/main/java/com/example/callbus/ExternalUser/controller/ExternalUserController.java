package com.example.callbus.ExternalUser.controller;

import com.example.callbus.ExternalUser.service.ExternalUserService;
import com.example.callbus.Member.dto.request.ExternalUserRequestDto;
import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
import com.example.callbus.Grobal.dto.ResponseDto;
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
public class ExternalUserController {

    private final ExternalUserService externaluserService;


    //회원가입
    @PostMapping("/signup")
    public ResponseDto<String> ExternalUserSignup(@RequestBody @Valid ExternalUserRequestDto externalUserRequestDto) {
        return externaluserService.ExternalUserSignup(externalUserRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> ExternalUserLogin(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                               HttpServletResponse response) {
        return externaluserService.ExternalUserLogin(loginRequestDto, response);
    }
}

