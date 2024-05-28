package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.entity.Application;
import com.seroja.easystudyapi.entity.Certificate;
import com.seroja.easystudyapi.entity.Course;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final EducationalMaterialRepository educationalMaterialRepository;
    private final EducationalMaterialMapper educationalMaterialMapper;
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private final UserRepository userRepository;
    private final TaskPerformanceRepository taskPerformanceRepository;
    private final TaskPerformanceMapper taskPerformanceMapper;

    public List<CourseDto> getAllMyCourses(Principal principal) {
        int userId = userRepository.findUserByUsername(principal.getName()).get().getId();
        List<Course> courses = courseRepository.findByUserId(userId);
        return courseMapper.toDtoList(courses);
    }

    public List<CourseDto> findCoursesByName(String name) {
        return courseMapper.toDtoList(courseRepository.findCoursesByCourseNameLike("%" + name + "%"));
    }

    public List<CourseDto> findCoursesByCategory(int id) {
        return courseMapper.toDtoList(courseRepository.findCoursesByCategoryCategoryId(id));
    }

    public List<CourseDto> sortCoursesByPrice(String order) {
        List<Course> courses = courseRepository.findAll();
        if (order.equals("ascending")) {
            courses.sort(Comparator.comparingInt(Course::getCoursePrice));
            return courseMapper.toDtoList(courses);
        } else if (order.equals("descending")) {
            courses.sort(Comparator.comparingInt(Course::getCoursePrice).reversed());
            return courseMapper.toDtoList(courses);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong order");
    }

    public List<CourseDto> sortByCourseStartDate(String order) {
        List<Course> courses = courseRepository.findAll();
        if (order.equals("ascending")) {
            courses.sort(Comparator.comparing(Course::getCourseStartDate));
            return courseMapper.toDtoList(courses);
        } else if (order.equals("descending")) {
            courses.sort(Comparator.comparing(Course::getCourseStartDate).reversed());
            return courseMapper.toDtoList(courses);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong order");
    }

    public List<ApplicationDto> getAllMyApplications(Principal principal) {
        int userId = userRepository.findUserByUsername(principal.getName()).get().getId();
        List<Application> applications = applicationRepository.findAllByStudentId(userId);
        return applicationMapper.toDtoList(applications);
    }

    public List<CertificateDto> getAllMyCertificates(Principal principal) {
        int userId = userRepository.findUserByUsername(principal.getName()).get().getId();
        List<Certificate> certificates = certificateRepository.findCertificateByApplicationStudentId(userId);
        return certificateMapper.toDtoList(certificates);
    }

    public EducationalMaterialDto getEducationalMaterialById(int id) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Material was not found!")));
    }

    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public TaskPerformanceDto createTaskPerformance(TaskPerformanceDto taskPerformanceDto) {
        return taskPerformanceMapper.toDto(taskPerformanceRepository.save(taskPerformanceMapper.toEntity(taskPerformanceDto)));
    }

}
