package com.example.callbus.Member.repository;

import com.example.callbus.Member.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByBoardIdAndMemberEmail(Long boardId, String email);

    //숫자 반환 글 아이디에 해당하는 like
    Long countAllByBoardId(Long boardId);
}

