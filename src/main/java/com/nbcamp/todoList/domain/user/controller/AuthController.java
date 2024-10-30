package com.nbcamp.todoList.domain.user.controller;

import com.nbcamp.todoList.domain.user.controller.dto.LoginRequest;
import com.nbcamp.todoList.domain.user.controller.dto.LoginResponse;
import com.nbcamp.todoList.domain.user.controller.dto.SignupRequest;
import com.nbcamp.todoList.domain.user.controller.dto.SignupResponse;
import com.nbcamp.todoList.domain.user.service.MemberService;
import com.nbcamp.todoList.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = memberService.registerMember(signupRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest, HttpServletResponse res) {
        LoginResponse loginResponse = memberService.login(loginRequest, res);

        jwtUtil.addJwtToCookie(loginResponse.getToken(), res);
        LoginResponse responseBody = new LoginResponse(null, loginResponse.getId(), loginResponse.getEmail(), loginResponse.getRole());

        return ResponseEntity.ok(responseBody);
    }

}
