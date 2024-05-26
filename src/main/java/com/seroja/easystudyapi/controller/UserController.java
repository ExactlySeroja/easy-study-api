package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.jwt.JwtRequest;
import com.seroja.easystudyapi.dto.jwt.JwtResponse;
import com.seroja.easystudyapi.service.AuthService;
import com.seroja.easystudyapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final AuthService authService;

    @GetMapping(Routes.TEACHER_USER_BY_ID)
    public ResponseEntity<UserDto> get(Principal principal) {
        try {
            return new ResponseEntity<>(service.getDto(principal), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_USER)
    public List<UserDto> list() {
        return service.listAll();
    }


    /*@PutMapping(Routes.TEACHER_USER_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid UserDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*@DeleteMapping(Routes.TEACHER_USER_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }*/

    @PostMapping(value = Routes.LOGIN)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping(value = Routes.REGISTER_ROUTE_SECURITY, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrationOfUser(@RequestBody UserDto dto) {
        return service.registerUser(dto);
    }

    @PostMapping(value = Routes.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@RequestBody JwtResponse oldToken) {
        return authService.refreshAuthToken(String.valueOf(oldToken));
    }

}
