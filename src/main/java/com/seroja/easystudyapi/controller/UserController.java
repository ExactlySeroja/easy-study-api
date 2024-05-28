package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.jwt.JwtRequest;
import com.seroja.easystudyapi.dto.jwt.JwtResponse;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.service.AuthService;
import com.seroja.easystudyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    //general
    @GetMapping(value = Routes.GET_ALL_COURSES)
    public List<CourseDto> getAllCourses() {
        return userService.getAllCourses();
    }

    //general
    @GetMapping(value = Routes.GET_ALL_THEMES_BY_COURSE)
    public List<ThemeDto> getAllThemesByCourse(@PathVariable int id) {
        return userService.getAllThemesByCourseId(id);
    }

    //general
    @GetMapping(value = Routes.GET_MATERIALS_BY_THEME)
    public List<EdMaterialAndTaskPerformanceQueryDto> getAllEducationalMaterialByTheme(@PathVariable int id) {
        return userService.getAllEducationalMaterialsByTheme(id);
    }

    //general
    @GetMapping(value = Routes.GET_ALL_CATEGORIES)
    public List<CategoryDto> getAllCategories() {
        return userService.getAllCategories();
    }

    @PostMapping(value = Routes.LOGIN)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping(value = Routes.REGISTER_ROUTE_SECURITY, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrationOfUser(@RequestBody UserDto dto) {
        return userService.registerUser(dto);
    }

    @PostMapping(value = Routes.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@RequestBody JwtResponse oldToken) {
        return authService.refreshAuthToken(String.valueOf(oldToken));
    }

    @GetMapping(value = Routes.CHECK_EXISTING_USER_EMAIL)
    public ResponseEntity<?> checkUserEmail(@RequestParam String email) {
        return userService.checkUserEmail(email);
    }

    @GetMapping(value = Routes.CHECK_EXISTING_USER_PHONE)
    public ResponseEntity<?> checkUserPhoneNumber(@RequestParam String phoneNumber) {
        return userService.checkUserPhoneNumber(phoneNumber);
    }

    @GetMapping(value = Routes.CHECK_EXISTING_USER_USERNAME)
    public ResponseEntity<?> checkUserUsername(@RequestParam String username) {
        return userService.checkUserUsername(username);
    }

}
