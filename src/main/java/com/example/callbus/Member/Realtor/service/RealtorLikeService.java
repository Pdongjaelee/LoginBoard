package com.example.callbus.Member.Realtor.service;

import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Grobal.exception.ErrorCode;
import com.example.callbus.Grobal.exception.RequestException;
import com.example.callbus.Member.Realtor.entity.Realtor;
import com.example.callbus.Member.Realtor.repository.RealtorRepository;
import com.example.callbus.Member.dto.response.LikeResponseDto;
import com.example.callbus.Member.entity.Board;
import com.example.callbus.Member.entity.Likes;
import com.example.callbus.Member.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RealtorLikeService {

    private final LikeRepository likeRepository;
    private final RealtorRepository realtorRepository;

    private Realtor getRealtor(String email) {
        Realtor realtor = realtorRepository.findByEmail(email).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404)
        );
        return realtor;
    }

    // 좋아요 체크(입력)
    @Transactional
    public ResponseDto<?> likeUp(Long boardId, String email) {
        // 들어온 boardId와 email 로 좋아요 여부 판단
        Optional<Likes> imsiLike = likeRepository.findByBoardIdAndMemberEmail(boardId, email);
        Realtor realtor = getRealtor(email);
        Board board = new Board(boardId);

        // 해당 글의 좋아요 true/false
        boolean correctLike;
        if (imsiLike.isPresent()) {
            // like 가 존재하면 삭제
            correctLike = false;
            likeRepository.delete(imsiLike.get());
        } else {
            // like가 없으면 등록
            correctLike = true;
            Likes like = new Likes(realtor, board);
            likeRepository.save(like);
        }

        // 해당 글의 총 좋아요 갯수
        Long countLike = likeRepository.countAllByBoardId(boardId);

        return ResponseDto.success(
                LikeResponseDto.builder()
                        .correctLike(correctLike)
                        .countLike(countLike)
                        .build()
        );
    }
}

