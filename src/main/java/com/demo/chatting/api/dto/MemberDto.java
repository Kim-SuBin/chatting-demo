package com.demo.chatting.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {

    @AllArgsConstructor
    @Getter
    public static class Member {
        private String nickName;
        private String password;
    }
}

