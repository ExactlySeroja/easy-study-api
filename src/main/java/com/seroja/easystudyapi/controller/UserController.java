package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.jwt.JwtRequest;
import com.seroja.easystudyapi.dto.jwt.JwtResponse;
import com.seroja.easystudyapi.dto.query.EducationalMaterialWithTaskPerformanceDto;
import com.seroja.easystudyapi.dto.query.GetCoursesRequestDto;
import com.seroja.easystudyapi.service.AuthService;
import com.seroja.easystudyapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping(value = Routes.GET_ALL_THEMES_BY_COURSE)
    public List<ThemeDto> getAllThemesByCourse(@PathVariable int id) {
        return userService.getAllThemesByCourseId(id);
    }

    @GetMapping(value = Routes.GET_MATERIALS_BY_THEME)
    public List<EducationalMaterialWithTaskPerformanceDto> getAllEducationalMaterialByTheme(@PathVariable int id, Principal principal) {
        return userService.getAllEducationalMaterialsWithTaskPerformance(id, principal);
    }

    @GetMapping(value = Routes.GET_THEME_BY_ID)
    public ThemeDto getTheme(@PathVariable int id) {
        return userService.getThemeById(id);
    }

    @GetMapping(value = Routes.GET_ALL_CATEGORIES)
    public List<CategoryDto> getAllCategories() {
        return userService.getAllCategories();
    }

    @GetMapping(value = Routes.GET_ALL_COURSES)
    public List<GetCoursesRequestDto> getAllCourses(
            @RequestParam(name = "name", required = false) String courseName,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(name = "minDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate minDate,
            @RequestParam(name = "maxDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate maxDate,
            @RequestParam(name = "priceSort", required = false) String priceSort,
            @RequestParam(name = "startDateSort", required = false) String startDateSort,
            @RequestParam(name = "endDateSort", required = false) String endDateSort,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            Principal principal) {
        return userService.filterCourses(courseName, categoryId, minPrice, maxPrice, minDate, maxDate, priceSort, startDateSort, endDateSort, limit, offset, principal);
    }

    @GetMapping(value = Routes.GET_COURSE_BY_ID)
    public CourseDto getCourseById(@PathVariable int id) {
        return userService.getCourseById(id);
    }

    @PostMapping(value = Routes.LOGIN)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping(value = Routes.REGISTER_ROUTE_SECURITY, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrationOfUser(@RequestBody @Valid UserDto dto) {
        return userService.registerUser(dto);
    }

    @PostMapping(value = Routes.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@RequestBody JwtResponse oldToken) {
        return authService.refreshAuthToken(oldToken.getToken());
    }

    @PostMapping(value = Routes.CHECK_EXISTING_USER_EMAIL)
    public ResponseEntity<?> checkUserEmail(@RequestParam String email) {
        return userService.checkUserEmail(email);
    }

    @PostMapping(value = Routes.CHECK_EXISTING_USER_PHONE)
    public ResponseEntity<?> checkUserPhoneNumber(@RequestParam String phoneNumber) {
        return userService.checkUserPhoneNumber(phoneNumber);
    }

    @PostMapping(value = Routes.CHECK_EXISTING_USER_USERNAME)
    public ResponseEntity<?> checkUserUsername(@RequestParam String username) {
        return userService.checkUserUsername(username);
    }

}
