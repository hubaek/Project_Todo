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
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRegisterRequest request) {
        MemberResponse memberResponse = memberService.registerMember(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberResponse);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long memberId) {
        MemberResponse memberResponse = memberService.getMemberById(memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest updateRequest) {
        MemberResponse memberResponse = memberService.updateMember(memberId, updateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long memberId) {
        MemberResponse memberResponse = memberService.deleteMember(memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

}
