package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = Routes.STUDENT_GET_ALL_AVAILABLE_COURSES)
    public List<CourseDto> getAllCourses() {
        return studentService.getAllCourses();
    }

    @GetMapping(value = Routes.STUDENT_GET_ALL_THEMES_BY_COURSE)
    public List<ThemeDto> getAllThemesByCourse(@PathVariable int courseId) {
        return studentService.getAllThemesByCourse(courseId);
    }

    @GetMapping(value = Routes.STUDENT_GET_ALL_MATERIALS_BY_THEME)
    public List<EdMaterialAndTaskPerformanceQueryDto> getAllEdMaterialAndTaskPerformance(@PathVariable int themeId) {
        return studentService.getAllEducationalMaterialsByTheme(themeId);
    }

    @GetMapping(value = Routes.STUDENT_FILTER_COURSE_BY_NAME)
    public List<CourseDto> findCoursesByName(@PathVariable String courseName) {
        return studentService.findCoursesByName(courseName);
    }

    @GetMapping(value = Routes.STUDENT_FILTER_COURSE_BY_CATEGORY)
    public List<CourseDto> findCourseByCategory(@PathVariable int categoryId) {
        return studentService.findCoursesByCategory(categoryId);
    }

    @GetMapping(value = Routes.STUDENT_FILTER_COURSE_BY_PRICE)
    public List<CourseDto> filterCoursesByPrice(@PathVariable String order) {
        return studentService.sortCoursesByPrice(order);
    }

    @GetMapping(value = Routes.STUDENT_FILTER_COURSE_BY_START_DATE)
    public List<CourseDto> sortCoursesByStartDate(@PathVariable String order) {
        return studentService.sortByCourseStartDate(order);
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
    public ResponseEntity<?> createApplication(ApplicationDto applicationDto) {
        try {
            return new ResponseEntity<>(studentService.createApplication(applicationDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = Routes.STUDENT_ADD_TASK_PERFORMANCE)
    public ResponseEntity<?> createTask(TaskPerformanceDto taskPerformanceDto) {
        try {
            return new ResponseEntity<>(studentService.createTaskPerformance(taskPerformanceDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
