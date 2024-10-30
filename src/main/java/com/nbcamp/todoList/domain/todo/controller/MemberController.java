package com.nbcamp.todoList.domain.todo.controller;

import com.nbcamp.todoList.domain.todo.controller.dto.MemberRegisterRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.MemberResponse;
import com.nbcamp.todoList.domain.todo.controller.dto.MemberUpdateRequest;
import com.nbcamp.todoList.domain.todo.entity.Member;
import com.nbcamp.todoList.domain.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public Member registerMember(@RequestBody MemberRegisterRequest request) {

        return memberService.registerMember(request);
    }

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest updateRequest) {
        MemberResponse memberResponse = memberService.updateMember(memberId, updateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

}
