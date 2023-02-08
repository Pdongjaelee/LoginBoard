package com.example.callbus.Member.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final String title;
    private final String contents;
}
