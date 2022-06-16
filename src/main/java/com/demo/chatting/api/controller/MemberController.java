package com.demo.chatting.api.controller;

import com.demo.chatting.api.dto.ApiResponse;
import com.demo.chatting.api.dto.MemberDto;
import com.demo.chatting.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<Long> signup(@RequestBody @Valid MemberDto.Member dto) {
        log.info("신규 멤버 회원가입");
        return ApiResponse.success(memberService.createMember(dto));
    }

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestBody @Valid MemberDto.Member dto) {
        log.info("멤버 로그인");
        return ApiResponse.success(memberService.loginMember(dto));
    }
}

