package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.ProfileDto;
import com.seroja.easystudyapi.entity.Course;
import com.seroja.easystudyapi.entity.TaskPerformance;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    private final EducationalMaterialRepository educationalMaterialRepository;
    private final EducationalMaterialMapper educationalMaterialMapper;

    private final TaskPerformanceRepository taskPerformanceRepository;
    private final TaskPerformanceMapper taskPerformanceMapper;

    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public List<CourseDto> getAllMyCourses(Principal principal) {
        int userId = userRepository.findUserByUsername(principal.getName()).get().getId();
        List<Course> courses = courseRepository.findByUserId(userId);
        return courseMapper.toDtoList(courses);
    }


    public CourseDto getCourseById(int id) {
        return courseMapper.toDto(courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Course was not found!")));
    }

    public CourseDto createCourse(CourseDto courseDto) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(courseDto)));
    }

    public ThemeDto createTheme(ThemeDto themeDto) {
        return themeMapper.toDto(themeRepository.save(themeMapper.toEntity(themeDto)));
    }


    public ThemeDto getThemeById(int id) {
        return themeMapper.toDto(themeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Theme was not found!")));
    }


    public EducationalMaterialDto getEducationalMaterialById(int id) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Material was not found!")));
    }

    public EducationalMaterialDto createEdMaterial(EducationalMaterialDto educationalMaterialDto) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.save(educationalMaterialMapper.toEntity(educationalMaterialDto)));
    }

    public List<TaskPerformanceDto> getAllTaskPerformancesByMaterial(int id) {
        return taskPerformanceMapper.toDtoList(taskPerformanceRepository.findByEdMaterialId(id));
    }

    public void updateTaskPerformanceGrade(int grade, int id) {
        TaskPerformance existingEntity = taskPerformanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        existingEntity.setGrade(grade);
        taskPerformanceRepository.save(existingEntity);
    }

    public CertificateDto createCertificate(CertificateDto certificateDto) {
        return certificateMapper.toDto(certificateRepository.save(certificateMapper.toEntity(certificateDto)));
    }

    public List<CertificateDto> getAllCertificates() {
        return certificateMapper.toDtoList(certificateRepository.findAll());
    }

    public CertificateDto getCertificateById(int id) {
        return certificateMapper.toDto(certificateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Material was not found!")));
    }

    public List<ApplicationDto> getAllApplications() {
        return applicationMapper.toDtoList(applicationRepository.findAll());
    }

    public ApplicationDto getApplication(int id) {
        return applicationMapper.toDto(applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }


    public List<ProfileDto> getAllStudents() {
        return userRepository.finAllStudents();
    }

    public ProfileDto getMyProfile(Principal principal) {
        return userMapper.toProfileDto(userRepository.findUserByUsername(principal.getName()).get());
    }


}
