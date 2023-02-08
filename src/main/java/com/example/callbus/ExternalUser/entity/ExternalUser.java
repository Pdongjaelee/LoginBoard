package com.example.callbus.ExternalUser.entity;

import com.example.callbus.Member.dto.request.ExternalUserRequestDto;
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
public class ExternalUser extends Timestamped {
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

    public ExternalUser(ExternalUserRequestDto externalUserRequestDto, Authority authority) {
        this.email = externalUserRequestDto.getEmail();
        this.password = externalUserRequestDto.getPassword();
        this.account_type = authority.ROLE_ExternalUser;
    }
}
