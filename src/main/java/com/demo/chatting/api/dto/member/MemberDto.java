package com.demo.chatting.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberDto {
    private String nickName;
    private String password;
}

