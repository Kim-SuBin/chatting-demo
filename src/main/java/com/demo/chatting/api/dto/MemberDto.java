package com.demo.chatting.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {

    @AllArgsConstructor
    @Getter
    public static class Signup {
        private String name;
        private String password;
    }
}

