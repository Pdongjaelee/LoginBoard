package com.example.callbus.ExternalUser.service;

import com.example.callbus.ExternalUser.entity.ExternalUser;
import com.example.callbus.ExternalUser.repository.ExternalUserRepository;
import com.example.callbus.Member.dto.request.ExternalUserRequestDto;
import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Grobal.dto.TokenDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.entity.Authority;
import com.example.callbus.Grobal.entity.RefreshToken;
import com.example.callbus.Grobal.exception.ErrorCode;
import com.example.callbus.Grobal.exception.RequestException;
import com.example.callbus.Grobal.jwt.JwtUtil;
import com.example.callbus.Grobal.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExternalUserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ExternalUserRepository externalUserRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseDto<String> ExternalUserSignup(ExternalUserRequestDto externalUserRequestDto) {

        if (externalUserRepository.findByEmail(externalUserRequestDto.getEmail()).isPresent()) {
            throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
        }
        externalUserRequestDto.setEncodedPwd(passwordEncoder.encode(externalUserRequestDto.getPassword()));
        ExternalUser externalUser  = new ExternalUser(externalUserRequestDto, Authority.ROLE_ExternalUser);

        externalUserRepository.save(externalUser);
        return ResponseDto.success("회원가입 완료");
    }

    // 로그인
    @Transactional
    public ResponseDto<LoginResponseDto> ExternalUserLogin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 이메일 있는지 확인
        ExternalUser externalUser = externalUserRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new RequestException(ErrorCode.LOGIN_NOT_FOUND_404)
        );
        // 비밀번호 있는지 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), externalUser.getPassword())) {
            throw new RequestException(ErrorCode.LOGIN_NOT_FOUND_404);
        }

        // 엑세스토콘, 리프레쉬토큰 생성
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

        // 리프레쉬 토큰은 DB에서 찾기
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(loginRequestDto.getEmail());

        // 리프레쉬토큰 null인지 아닌지 에 따라서
        // 값을 가지고있으면 save
        // 값이 없으면 newToken 만들어내서 save
        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return ResponseDto.success(
                LoginResponseDto.builder()
                        .authority(externalUser.getAccount_type())
                        .build()
        );
    }

    // response에 담는 메서드
    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

}

