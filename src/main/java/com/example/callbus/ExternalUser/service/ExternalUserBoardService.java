package com.example.callbus.ExternalUser.service;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ExternalUserBoardService {

    private final BoardRepository boardRepository;

    //글 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> externalShow() {
        return ResponseDto.success(boardRepository.findAllByOrderByModifiedAtDesc());
        //글 작성자 타입 표시(한글)
        //글 목록 중 자신의 좋아요 확인 가능
        //글에 달린 좋아요 수 표시
    }

}
