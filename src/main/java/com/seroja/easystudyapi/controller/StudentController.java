package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.CertificateDto;
import com.seroja.easystudyapi.dto.CreateApplicationRequestDto;
import com.seroja.easystudyapi.dto.EducationalMaterialDto;
import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import com.seroja.easystudyapi.dto.query.ApplicationWithFullInfoDto;
import com.seroja.easystudyapi.dto.query.GetCoursesRequestDto;
import com.seroja.easystudyapi.service.StudentService;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = Routes.STUDENT_GET_ALL_MY_COURSES)
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
        return studentService.filterCourses(courseName, categoryId, minPrice, maxPrice, minDate, maxDate, priceSort, startDateSort, endDateSort, limit, offset, principal);
    }

    @GetMapping(value = Routes.STUDENT_GET_ALL_MY_APPLICATIONS)
    public List<ApplicationWithFullInfoDto> getAllMyApplications(Principal principal) {
        return studentService.getAllMyApplications(principal);
    }

    @GetMapping(value = Routes.STUDENT_GET_MY_TASK_PERFORMANCE_BY_MATERIAL)
    public Optional<TaskPerformanceDto> getTaskPerformanceByMaterial(@PathVariable Integer materialId, Principal principal) {
        return studentService.getTaskPerformanceByMaterialId(materialId, principal);
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
    public ResponseEntity<?> createApplication(@RequestBody @Valid CreateApplicationRequestDto createApplicationRequestDto, Principal principal) {
        try {
            return new ResponseEntity<>(studentService.createApplication(createApplicationRequestDto, principal), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = Routes.STUDENT_ADD_TASK_PERFORMANCE)
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskPerformanceDto taskPerformanceDto) {
        try {
            return new ResponseEntity<>(studentService.createTaskPerformance(taskPerformanceDto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
