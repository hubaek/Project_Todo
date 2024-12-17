package com.nbcamp.todoList.domain.user.service;

import com.nbcamp.todoList.domain.user.controller.dto.*;
import com.nbcamp.todoList.domain.user.entity.Member;
import com.nbcamp.todoList.domain.user.entity.UserRole;
import com.nbcamp.todoList.domain.user.repository.MemberRepository;
import com.nbcamp.todoList.exception.MemberAlreadyExistsException;
import com.nbcamp.todoList.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final JwtUtil jwtUtil;

    public MemberResponse createMember(MemberRegisterRequest request) {
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

    public SignupResponse registerMember(SignupRequest signupRequest) {
        String name = signupRequest.getName();
        Optional<Member> checkUsername = memberRepository.findByName(name);
        if (checkUsername.isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        String email = signupRequest.getEmail();
        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (signupRequest.isAdmin()) {
            if (!ADMIN_TOKEN.equals(signupRequest.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }

        String password = passwordEncoder.encode(signupRequest.getPassword());
        Member member = new Member(name, email, password, role);
        memberRepository.save(member);
        return new SignupResponse(member);
    }

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse res) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(member.getId(), member.getRole(), member.getName(), member.getEmail());
        res.setHeader("Authorization", token);

        return new LoginResponse(token, member.getId(), member.getEmail(), member.getRole());
    }
}
