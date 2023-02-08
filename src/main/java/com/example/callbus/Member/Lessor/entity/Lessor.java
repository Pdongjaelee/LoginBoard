package com.example.callbus.Member.Lessor.entity;

import com.example.callbus.Member.dto.request.MemberRequestDto;
import com.example.callbus.Member.entity.Authority;
import com.example.callbus.Member.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lessor extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority account_type;

    @Column(nullable = false)
    private boolean quit;

    public Lessor(MemberRequestDto memberRequestDto, Authority authority) {
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();
        this.account_type = authority.ROLE_Member;
    }
}
