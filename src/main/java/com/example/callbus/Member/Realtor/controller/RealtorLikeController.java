package com.example.callbus.Member.Realtor.controller;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Grobal.security.UserDetailsImpl;
import com.example.callbus.Member.Realtor.service.RealtorLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class RealtorLikeController {

    private final RealtorLikeService realtorLikeService;

    //좋아요 체크
    @PostMapping("/board/{boardId}/like")
    public ResponseDto<?> likeUp(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return realtorLikeService.likeUp(id, userDetailsImpl.getMember().getEmail());
    }
}

