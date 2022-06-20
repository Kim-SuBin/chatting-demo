package com.demo.chatting.api.command.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberCommand {
    private String nickName;
    private String password;
}

