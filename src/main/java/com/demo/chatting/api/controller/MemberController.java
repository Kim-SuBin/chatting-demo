package com.demo.chatting.api.controller;

import com.demo.chatting.api.dto.MemberDto;
import com.demo.chatting.api.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/member/signup")
    public ResponseEntity<Long> signup(@RequestBody @Valid MemberDto.Member dto) {
        log.info("신규 멤버 회원가입");
        return ResponseEntity.ok().body(memberService.createMember(dto));
    }

    @PostMapping("/member/login")
    public ResponseEntity<Long> login(@RequestBody @Valid MemberDto.Member dto) {
        log.info("멤버 로그인");
        return ResponseEntity.ok().body(memberService.loginMember(dto));
    }
}
