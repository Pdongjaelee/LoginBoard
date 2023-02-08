package com.example.callbus.Member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}

