package com.nbcamp.todoList.domain.todo.controller;

import com.nbcamp.todoList.domain.todo.controller.dto.MemberRegisterRequest;
import com.nbcamp.todoList.domain.todo.entity.Member;
import com.nbcamp.todoList.domain.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public Member registerMember(@RequestBody MemberRegisterRequest request) {

        return memberService.registerMember(request);
    }

    @GetMapping("/members/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

}
