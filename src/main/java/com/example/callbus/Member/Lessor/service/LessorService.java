package com.example.callbus.Member.Lessor.service;

import com.example.callbus.Member.dto.request.LoginRequestDto;
import com.example.callbus.Member.dto.request.MemberRequestDto;
import com.example.callbus.Grobal.dto.TokenDto;
import com.example.callbus.Member.dto.response.LoginResponseDto;
import com.example.callbus.Grobal.dto.ResponseDto;
import com.example.callbus.Member.entity.Authority;
import com.example.callbus.Member.Lessor.entity.Lessor;
import com.example.callbus.Grobal.entity.RefreshToken;
import com.example.callbus.Grobal.exception.ErrorCode;
import com.example.callbus.Grobal.exception.RequestException;
import com.example.callbus.Grobal.jwt.JwtUtil;
import com.example.callbus.Member.Lessor.repository.LessorRepository;
import com.example.callbus.Grobal.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LessorService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final LessorRepository lessorRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseDto<String> LessorSignup(MemberRequestDto memberRequestDto) {

        if (lessorRepository.findByEmail(memberRequestDto.getEmail()).isPresent()) {
            throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
        }
        memberRequestDto.setEncodedPwd(passwordEncoder.encode(memberRequestDto.getPassword()));
        Lessor lessor  = new Lessor(memberRequestDto, Authority.ROLE_Lessor);

        lessorRepository.save(lessor);
        return ResponseDto.success("회원가입 완료");
    }

    // 로그인
    @Transactional
    public ResponseDto<LoginResponseDto> LessorLogin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 이메일 있는지 확인
        Lessor lessor = lessorRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new RequestException(ErrorCode.LOGIN_NOT_FOUND_404)
        );
        // 비밀번호 있는지 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), lessor.getPassword())) {
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
                        .authority(lessor.getAccount_type())
                        .build()
        );
    }

    // response에 담는 메서드
    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

}

