package com.example.callbus.Member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ExternalUserRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 값 입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;


    @NotBlank(message = "비밀번호는 필수 입력 값 입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 한다")
    private String password;

    @NotBlank
    private String passwordConfirm;

    public ExternalUserRequestDto(String email, String nickname, String password, String passwordConfirm) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public void setEncodedPwd(String encodedPwd) {
        this.password = encodedPwd;
    }
}

