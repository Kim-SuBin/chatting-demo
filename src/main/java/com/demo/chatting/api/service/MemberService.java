package com.demo.chatting.api.service;

import com.demo.chatting.api.domain.Member;
import com.demo.chatting.api.dto.member.MemberDto;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    Member member;

    /**
     * 이름 중복 확인
     *
     * @param nickName 멤버가 설정한 이름
     */
    public void validateDuplicateMember(String nickName) {
        Optional<Member> findMember = memberRepository.findByNickName(nickName);
        if (findMember.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }

    /**
     * 멤버 생성
     *
     * @param dto 멤버 회원가입 정보
     * @return 멤버 인덱스
     */
    public Long createMember(MemberDto dto) {
        validateDuplicateMember(dto.getNickName());
        member = new Member(dto.getNickName(), passwordEncoder.encode(dto.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 멤버 로그인
     *
     * @param dto 멤버 로그인 정보
     * @return 멤버 인덱스
     */
    public Long loginMember(MemberDto dto) {
        member = memberRepository.findByNickName(dto.getNickName()).orElseThrow(() -> new CustomException(ErrorCode.INVALID_LOGIN_INPUT));
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN_INPUT);
        }
        return member.getId();
    }
}