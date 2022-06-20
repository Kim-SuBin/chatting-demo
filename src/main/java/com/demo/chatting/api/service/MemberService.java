package com.demo.chatting.api.service;

import com.demo.chatting.api.command.member.MemberCommand;
import com.demo.chatting.api.domain.Member;
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
     * @param command 멤버 회원가입 정보
     * @return 멤버 인덱스
     */
    public Long createMember(MemberCommand command) {
        validateDuplicateMember(command.getNickName());
        member = new Member(command.getNickName(), passwordEncoder.encode(command.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 멤버 로그인
     *
     * @param command 멤버 로그인 정보
     * @return 멤버 인덱스
     */
    public Long loginMember(MemberCommand command) {
        member = memberRepository.findByNickName(command.getNickName()).orElseThrow(() -> new CustomException(ErrorCode.INVALID_LOGIN_INPUT));
        if (!passwordEncoder.matches(command.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN_INPUT);
        }
        return member.getId();
    }
}