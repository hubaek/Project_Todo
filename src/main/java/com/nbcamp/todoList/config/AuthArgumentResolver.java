package com.nbcamp.todoList.config;

import com.nbcamp.todoList.common.annotation.Auth;
import com.nbcamp.todoList.common.dto.AuthMember;
import com.nbcamp.todoList.domain.user.entity.UserRole;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.getParameterAnnotation(Auth.class) != null;
        boolean isAuthMemberType = parameter.getParameterType().equals(AuthMember.class);

        // @Auth 어노테이션과 AuthUser 타입이 함께 사용되지 않은 경우 예외 발생
        if (hasAuthAnnotation != isAuthMemberType) {
//            throw new AuthException("@Auth와 AuthUser 타입은 함께 사용되어야 합니다.");
            throw new IllegalArgumentException("@Auth와 AuthUser 타입은 함께 사용되어야 합니다.");
        }
        return hasAuthAnnotation;
    }

    @Override
    public Object resolveArgument(
            @Nullable MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // JwtFilter 에서 set 한 memberId, email, userRole 값을 가져옴
        Long memberId = (Long) request.getAttribute("userId");
        String email = (String) request.getAttribute("email");
        UserRole userRole = UserRole.of((String) request.getAttribute("userRole"));
        String name = (String) request.getAttribute("name");

        return new AuthMember(memberId, email, userRole, name);
    }
}
