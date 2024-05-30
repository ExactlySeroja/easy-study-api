package com.seroja.easystudyapi.security;

import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.jwt.JwtResponse;
import com.seroja.easystudyapi.service.UserService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.lifetime}")
    private Long jwtLifetime;
    private final UserService userService;

    public String createToken(UserDetails userDetails, UserDto userDto) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("id", userDto.getId());
        claims.put("fullName", userDto.getFullName());
        claims.put("username", userDto.getUsername());
        claims.put("email", userDto.getEmail());
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        Date now = new Date();
        Date expireTime = new Date(now.getTime() + jwtLifetime);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public JwtResponse refreshToken(String token) {
        if (validateToken(token)) {
            Claims claims = getClaimsFromToken(token);
            Date now = new Date();
            Date newExpirationDate = new Date(now.getTime() + jwtLifetime);

            claims.setExpiration(newExpirationDate);

            String newToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(newExpirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            return new JwtResponse(newToken);

        }
        throw new IllegalArgumentException("Invalid token");
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return getClaimsFromToken(token).get("role", List.class);
    }


    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
