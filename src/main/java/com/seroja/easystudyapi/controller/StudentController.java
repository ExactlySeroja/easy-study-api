package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = Routes.STUDENT_GET_ALL_MY_COURSES)
    public List<CourseDto> getAllMyCourses(Principal principal) {
        return studentService.getAllMyCourses(principal);
    }

    @GetMapping(value = Routes.STUDENT_GET_ALL_MY_APPLICATIONS)
    public List<ApplicationDto> getAllMyApplications(Principal principal) {
        return studentService.getAllMyApplications(principal);
    }

    @GetMapping(value = Routes.STUDENT_GET_ALL_MY_CERTIFICATES)
    public List<CertificateDto> getAllMyCertificates(Principal principal) {
        return studentService.getAllMyCertificates(principal);
    }

    @GetMapping(value = Routes.STUDENT_GET_MATERIAL)
    public EducationalMaterialDto getEdMaterial(@PathVariable int materialId) {
        return studentService.getEducationalMaterialById(materialId);
    }

    @PostMapping(value = Routes.STUDENT_CREATE_APPLICATION)
    public ResponseEntity<?> createApplication(@RequestBody ApplicationRequestDto applicationRequestDto, Principal principal) {
        try {
            return new ResponseEntity<>(studentService.createApplication(applicationRequestDto, principal), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = Routes.STUDENT_ADD_TASK_PERFORMANCE)
    public ResponseEntity<?> createTask(@RequestBody TaskPerformanceDto taskPerformanceDto) {
        try {
            return new ResponseEntity<>(studentService.createTaskPerformance(taskPerformanceDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
