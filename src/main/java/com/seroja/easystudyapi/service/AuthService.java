package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.jwt.JwtRequest;
import com.seroja.easystudyapi.dto.jwt.JwtResponse;
import com.seroja.easystudyapi.exceptions.AuthErrorException;
import com.seroja.easystudyapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthErrorException(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        UserDto userDto = userService.getDtoByUsername(authRequest.getUsername());
        String token = jwtTokenProvider.createToken(userDetails, userDto);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> refreshAuthToken(String token) {
        return ResponseEntity.ok(jwtTokenProvider.refreshToken(token));
    }

}