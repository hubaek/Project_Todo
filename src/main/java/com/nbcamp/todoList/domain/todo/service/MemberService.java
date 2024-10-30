package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.controller.dto.MemberRegisterRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.MemberResponse;
import com.nbcamp.todoList.domain.todo.controller.dto.MemberUpdateRequest;
import com.nbcamp.todoList.domain.todo.entity.Member;
import com.nbcamp.todoList.domain.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(MemberRegisterRequest request) {
        Member member = new Member(request.getName(), request.getEmail());
        return memberRepository.save(member);
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다!"));
    }

    public MemberResponse updateMember(Long memberId, MemberUpdateRequest updateRequest) {
        Member member = getMember(memberId);
        member.update(updateRequest.getName());
        memberRepository.save(member);
        return new MemberResponse(member);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다."));
    }

}
