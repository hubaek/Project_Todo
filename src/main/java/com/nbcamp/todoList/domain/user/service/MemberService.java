package com.nbcamp.todoList.domain.user.service;

import com.nbcamp.todoList.domain.user.controller.dto.MemberRegisterRequest;
import com.nbcamp.todoList.domain.user.controller.dto.MemberResponse;
import com.nbcamp.todoList.domain.user.controller.dto.MemberUpdateRequest;
import com.nbcamp.todoList.domain.user.entity.Member;
import com.nbcamp.todoList.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse registerMember(MemberRegisterRequest request) {
        Member member = new Member(request.getName(), request.getEmail());
        memberRepository.save(member);
        return new MemberResponse(member);
    }

    public MemberResponse getMemberById(Long memberId) {
        Member member = getMember(memberId);
        return new MemberResponse(member);
    }

    public MemberResponse updateMember(Long memberId, MemberUpdateRequest updateRequest) {
        Member member = getMember(memberId);
        member.update(updateRequest.getName());
        memberRepository.save(member);
        return new MemberResponse(member);
    }

    public MemberResponse deleteMember(Long memberId) {
        Member member = getMember(memberId);
        memberRepository.delete(member);
        return new MemberResponse(member);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다."));
    }
}
