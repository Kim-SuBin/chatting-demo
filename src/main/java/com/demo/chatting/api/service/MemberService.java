package com.demo.chatting.api.service;

import com.demo.chatting.api.domain.Member;
import com.demo.chatting.api.dto.MemberDto;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    Member member;

    public void validateDuplicateMember(String name) {
        Optional<Member> findMember = memberRepository.findByName(name);
        log.info("찾은 멤버 정보 : " + findMember);
        if (findMember.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_NAME);
        }
    }

    public Long createMember(MemberDto.Member dto) {
        validateDuplicateMember(dto.getName());
        member = new Member(dto.getName(), passwordEncoder.encode(dto.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    public Long loginMember(MemberDto.Member dto) {
        member = memberRepository.findByName(dto.getName()).orElseThrow(() -> new CustomException(ErrorCode.INVALID_LOGIN_INPUT));
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN_INPUT);
        }
        return member.getId();
    }
}