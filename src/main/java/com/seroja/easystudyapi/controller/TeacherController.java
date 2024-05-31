package com.seroja.easystudyapi.controller;


import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.*;
import com.seroja.easystudyapi.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(value = Routes.TEACHER_GET_ALL_MY_COURSES)
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
        return teacherService.filterCourses(courseName, categoryId, minPrice, maxPrice, minDate, maxDate, priceSort, startDateSort, endDateSort, limit, offset, principal);
    }

    @PutMapping(value = Routes.TEACHER_DELETE_UPDATE_COURSE)
    public ResponseEntity<?> updateCourse(@RequestBody @Valid CourseDto courseDto, @PathVariable Integer id) {
        teacherService.updateCourse(courseDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = Routes.TEACHER_DELETE_UPDATE_COURSE)
    public void deleteCourse(@PathVariable Integer id) {
        teacherService.deleteCourse(id);
    }

    @PutMapping(value = Routes.TEACHER_GET_THEME_BY_ID)
    public ResponseEntity<?> updateTheme(@RequestBody @Valid ThemeDto themeDto, @PathVariable Integer id) {
        teacherService.updateTheme(themeDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = Routes.TEACHER_GET_THEME_BY_ID)
    public void deleteTheme(@PathVariable Integer id) {
        teacherService.deleteTheme(id);
    }

    @PutMapping(value = Routes.TEACHER_GET_MATERIAL_BY_ID)
    public ResponseEntity<?> updateMaterial(@RequestBody @Valid EducationalMaterialDto educationalMaterialDto, @PathVariable Integer id) {
        teacherService.updateEdMaterial(educationalMaterialDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = Routes.TEACHER_GET_MATERIAL_BY_ID)
    public void deleteMaterial(@PathVariable Integer id) {
        teacherService.deleteEdMaterial(id);
    }

    @PostMapping(value = Routes.TEACHER_CREATE_NEW_COURSE)
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseDto courseDto) {
        try {
            return new ResponseEntity<>(teacherService.createCourse(courseDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = Routes.TEACHER_CREATE_THEME)
    public ResponseEntity<?> createTheme(@RequestBody @Valid ThemeDto themeDto) {
        try {
            return new ResponseEntity<>(teacherService.createTheme(themeDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = Routes.TEACHER_GET_STUDENTS_BY_COURSE_ID)
    public List<StudentTaskPerformanceDto> getStudentsByCourseId(@PathVariable Integer id) {
        return teacherService.getStudentTaskPerformances(id);
    }

    @GetMapping(value = Routes.TEACHER_GET_PERFORMANCE_BY_STUDENT_ID)
    public List<TaskPerformanceDto> getTaskPerformancesByStudentId(@PathVariable Integer id) {
        return teacherService.getTaskPerformancesByStudentId(id);
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
    public ResponseEntity<?> updateApplicationStatus(@PathVariable int id) {
        teacherService.updateApplicationStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = Routes.TEACHER_CREATE_NEW_CERTIFICATE)
    public ResponseEntity<?> createCertificate(@RequestBody @Valid CertificateDto certificateDto) {
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
    public List<ApplicationWithFullInfoDto> getAllApplications(Principal principal) {
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
