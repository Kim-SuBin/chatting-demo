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

    public void validateDuplicateMember(String name) {
        Optional<Member> findMember = memberRepository.findByName(name);
        log.info("찾은 멤버 정보 : " + findMember);
        if (findMember.isPresent()) {
            throw new CustomException(ErrorCode.NAME_DUPLICATION);
        }
    }

    public Long createMember(MemberDto.Signup dto) {
        validateDuplicateMember(dto.getName());
        Member member = new Member(dto.getName(), passwordEncoder.encode(dto.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }
}