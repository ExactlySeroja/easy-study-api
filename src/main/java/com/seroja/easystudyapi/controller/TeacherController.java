package com.seroja.easystudyapi.controller;


import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.dto.query.ProfileDto;
import com.seroja.easystudyapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(value = Routes.TEACHER_GET_ALL_MY_COURSES)
    public List<CourseDto> getAllMyCourses(Principal principal) {
        return teacherService.getAllMyCourses(principal);
    }

    @GetMapping(value = Routes.TEACHER_GET_COURSE_BY_ID)
    public CourseDto getCourseById(@PathVariable int id) {
        return teacherService.getCourseById(id);
    }

    @PostMapping(value = Routes.TEACHER_CREATE_NEW_COURSE)
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        try {
            return new ResponseEntity<>(teacherService.createCourse(courseDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = Routes.TEACHER_CREATE_THEME)
    public ResponseEntity<?> createTheme(@RequestBody ThemeDto themeDto) {
        try {
            return new ResponseEntity<>(teacherService.createTheme(themeDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = Routes.TEACHER_GET_THEME_BY_ID)
    public ThemeDto getTheme(@PathVariable int id) {
        return teacherService.getThemeById(id);
    }


    @GetMapping(value = Routes.TEACHER_GET_MATERIAL_BY_ID)
    public EducationalMaterialDto getEdMaterial(@PathVariable int id) {
        return teacherService.getEducationalMaterialById(id);
    }

    @PostMapping(value = Routes.TEACHER_CREATE_NEW_MATERIAL)
    public ResponseEntity<?> createEdMaterial(@RequestBody EducationalMaterialDto educationalMaterialDto) {
        try {
            return new ResponseEntity<>(teacherService.createEdMaterial(educationalMaterialDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = Routes.TEACHER_GET_ALL_TASK_PERFORMANCE_BY_MATERIAL)
    public List<EdMaterialAndTaskPerformanceQueryDto> getAllTaskPerformancesByMaterial(@PathVariable int id) {
        return teacherService.findEducationalMaterialAndTaskPerformanceById(id);
    }

    @PutMapping(value = Routes.TEACHER_UPDATE_PERFORMANCE)
    public ResponseEntity<?> updateTaskPerformance(@RequestBody int grade, @PathVariable int id) {
        teacherService.updateTaskPerformanceGrade(grade, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Routes.TEACHER_GET_APPLICATION_BY_ID)
    public ResponseEntity<?> updateApplicationStatus(@RequestBody boolean status, @PathVariable int id) {
        teacherService.updateApplicationStatus(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = Routes.TEACHER_CREATE_NEW_CERTIFICATE)
    public ResponseEntity<?> createCertificate(@RequestBody CertificateDto certificateDto) {
        try {
            return new ResponseEntity<>(teacherService.createCertificate(certificateDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = Routes.TEACHER_GET_ALL_CERTIFICATES)
    public List<CertificateDto> getAllThemesByCourse() {
        return teacherService.getAllCertificates();
    }

    @GetMapping(value = Routes.TEACHER_GET_CERTIFICATE_BY_ID)
    public CertificateDto getCertificateById(@PathVariable int id) {
        return teacherService.getCertificateById(id);
    }

    @GetMapping(value = Routes.TEACHER_GET_ALL_APPLICATIONS)
    public List<ApplicationDto> getAllApplications(Principal principal) {
        return teacherService.getAllApplications(principal);
    }

    @GetMapping(value = Routes.TEACHER_GET_APPLICATION_BY_ID)
    public ApplicationDto getApplication(@PathVariable int id) {
        return teacherService.getApplication(id);
    }

    @GetMapping(value = Routes.TEACHER_GET_STUDENTS)
    public List<ProfileDto> getAllStudents() {
        return teacherService.getAllStudents();
    }

    @GetMapping(value = Routes.TEACHER_MY_PROFILE)
    public ProfileDto getMyProfile(Principal principal) {
        return teacherService.getMyProfile(principal);
    }

}
