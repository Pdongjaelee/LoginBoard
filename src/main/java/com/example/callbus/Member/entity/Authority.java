package com.example.callbus.Member.entity;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_REALTOR(1),
    ROLE_Lessor(2),
    ROLE_Lessee(3),
    ROLE_ExternalUser(4),
    ROLE_Member(5);

    private int num;
    Authority(int num) {
        this.num = num;
    }

}
