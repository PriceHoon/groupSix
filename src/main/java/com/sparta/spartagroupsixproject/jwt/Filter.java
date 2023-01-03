package com.sparta.spartagroupsixproject.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Filter {

    private final JwtUtil jwtUtil;

    public String checkUser(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaims(token);
        String username = claims.getSubject();
        return username;
    }

    private Claims createClaims(String token){
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                return claims;
            }
            else{
                throw new IllegalArgumentException("해당 토큰은 유효하지않습니다");
            }
        }else{
            throw new IllegalArgumentException("해당 토큰은 값을 가지고 있지않습니다");
        }
    }

}
