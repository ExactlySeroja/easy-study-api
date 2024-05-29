package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.entity.Application;
import com.seroja.easystudyapi.entity.Certificate;
import com.seroja.easystudyapi.entity.Course;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
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

    @Transactional
    public ApplicationDto createApplication(ApplicationRequestDto applicationRequestDto, Principal principal) {
        ApplicationDto applicationDto = new ApplicationDto();
        int userId = userRepository.findUserByUsername(principal.getName()).get().getId();
        applicationDto.setStudentId(userId);
        applicationDto.setCourseId(applicationRequestDto.getCourseId());
        applicationDto.setDateOfCreation(LocalDate.now());
        applicationDto.setApplicationStatus(false);
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public TaskPerformanceDto createTaskPerformance(TaskPerformanceDto taskPerformanceDto) {
        return taskPerformanceMapper.toDto(taskPerformanceRepository.save(taskPerformanceMapper.toEntity(taskPerformanceDto)));
    }

}
