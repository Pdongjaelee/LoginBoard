package com.example.callbus.Grobal.exception;

import com.example.callbus.Grobal.dto.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseDto<Object> handleApiRequestException(RequestException e) {

        return ResponseDto.fail(
                e.getHttpStatus(),
                e.getMessage()
        );
    }
}

