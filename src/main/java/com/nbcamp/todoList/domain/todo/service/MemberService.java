package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.controller.dto.MemberRegisterRequest;
import com.nbcamp.todoList.domain.todo.entity.Member;
import com.nbcamp.todoList.domain.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(MemberRegisterRequest request) {
        Member member = new Member(request.getName());
        return memberRepository.save(member);
    }
}
