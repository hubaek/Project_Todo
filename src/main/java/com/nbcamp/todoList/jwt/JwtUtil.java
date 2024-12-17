package com.nbcamp.todoList.jwt;

import com.nbcamp.todoList.domain.user.entity.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";
    // 토큰 만료시간
    private static final long TOKEN_EXPIRATION_TIME = 60 * 60 * 1000L; // 60분

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKey;
    private SecretKey key;

    // todo Logger를 @Slf4j로 변경
    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 생성
    public String createToken(Long memberId, UserRoleEnum userRole, String name, String email) {
        Date now = new Date();

        return BEARER_PREFIX + Jwts.builder()
                .setSubject(String.valueOf(memberId)) // 사용자 식별자값(ID)
                .claim("userRole", userRole)
                .claim("name", name)
                .claim("email", email)
                .signWith(key) // 암호화 알고리즘
                .setIssuedAt(now) // 발급일
                .setExpiration(new Date(now.getTime() + TOKEN_EXPIRATION_TIME))
                .compact();
    }

    public String substringToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    public Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (JwtException e) {
            logger.error("토큰 검증 실패: {}", e.getMessage());
            throw new JwtException("유효하지 않은 JWT 토큰", e);
        }
    }

//    public Claims getClaimsFromToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }

    public Claims getClaimsFromToken(String token) {
        return validateToken(substringToken(token)).getBody();
    }



}


