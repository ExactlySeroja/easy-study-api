package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.*;
import com.seroja.easystudyapi.entity.*;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import com.seroja.easystudyapi.specification.CourseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseSpecification courseSpecification;

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

    public List<GetCoursesRequestDto> filterCourses(
            String courseName, Integer categoryId, Integer minPrice, Integer maxPrice, LocalDate minDate, LocalDate maxDate,
            String priceSort, String startDateSort, String endDateSort, int limit, int offset, Principal principal) {
        Pageable pageable = PageRequest.of(offset / limit, limit, courseSpecification.getSort(priceSort, startDateSort, endDateSort));

        Specification<Course> spec = Specification.where(courseSpecification.hasTeacherId(getId(principal)))
                .and(courseSpecification.hasName(courseName))
                .and(courseSpecification.hasCategoryId(categoryId))
                .and(courseSpecification.hasMinPrice(minPrice))
                .and(courseSpecification.hasMaxPrice(maxPrice))
                .and(courseSpecification.hasMinDate(minDate))
                .and(courseSpecification.hasMaxDate(maxDate));

        return userService.getGetCoursesRequestDtos(priceSort, startDateSort, endDateSort, principal, spec, pageable);
    }

    private Integer getId(Principal principal) {
        return userRepository.findUserByUsername(principal.getName()).get().getId();
    }


    public CourseDto createCourse(CourseDto courseDto) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(courseDto)));
    }

    public ThemeDto createTheme(ThemeDto themeDto) {
        return themeMapper.toDto(themeRepository.save(themeMapper.toEntity(themeDto)));
    }


    public EducationalMaterialDto getEducationalMaterialById(int id) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404))));
    }

    public EducationalMaterialDto createEdMaterial(EducationalMaterialDto educationalMaterialDto) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.save(educationalMaterialMapper.toEntity(educationalMaterialDto)));
    }

    public List<EdMaterialAndTaskPerformanceQueryDto> findEducationalMaterialAndTaskPerformanceById(int edMaterialId) {
        List<EdMaterialAndTaskPerformanceProjection> projections = educationalMaterialRepository.findEducationalMaterialAndTaskPerformanceById(edMaterialId);
        return projections.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EdMaterialAndTaskPerformanceQueryDto convertToDto(EdMaterialAndTaskPerformanceProjection projection) {
        EdMaterialAndTaskPerformanceQueryDto dto = new EdMaterialAndTaskPerformanceQueryDto();
        dto.setId(projection.getId());
        dto.setName(projection.getName());
        dto.setDateOfUpload(projection.getDateOfUpload());
        if (projection.getTaskPerformanceDtoId() != null) {
            TaskPerformanceDto taskDto = getTaskPerformanceDto(projection);
            dto.setTaskPerformanceDto(taskDto);
        }
        return dto;
    }

    private static TaskPerformanceDto getTaskPerformanceDto(EdMaterialAndTaskPerformanceProjection projection) {
        TaskPerformanceDto taskDto = new TaskPerformanceDto();
        taskDto.setId(projection.getTaskPerformanceDtoId());
        taskDto.setEdMaterialId(projection.getTaskPerformanceDtoEdMaterialId());
        taskDto.setStudentId(projection.getTaskPerformanceDtoStudentId());
        taskDto.setDateOfCompletion(projection.getTaskPerformanceDtoDateOfCompletion());
        taskDto.setAnswer(projection.getTaskPerformanceDtoAnswer());
        taskDto.setGrade(projection.getTaskPerformanceDtoGrade());
        return taskDto;
    }

    public void updateTaskPerformanceGrade(int grade, int id) {
        TaskPerformance existingEntity = taskPerformanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingEntity.setGrade(grade);
        taskPerformanceRepository.save(existingEntity);
    }

    public void updateApplicationStatus(int id) {
        Application existingEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingEntity.setApplicationStatus(true);
        applicationRepository.save(existingEntity);
    }

    public CertificateDto createCertificate(CertificateDto certificateDto) {
        return certificateMapper.toDto(certificateRepository.save(certificateMapper.toEntity(certificateDto)));
    }

    public List<CertificateDto> getAllCertificates() {
        return certificateMapper.toDtoList(certificateRepository.findAll());
    }

    public CertificateDto getCertificateById(int id) {
        return certificateMapper.toDto(certificateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404))));
    }

    public List<ApplicationWithFullInfoDto> getAllApplications(Principal principal) {
        return applicationMapper.toWithFullInfoDtoList(applicationRepository.findAllApplicationsByTeacherId(getId(principal)));
    }

    public ApplicationDto getApplication(int id) {
        return applicationMapper.toDto(applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<ProfileDto> getAllStudents() {
        return userMapper.toProfileDtoList(userRepository.findAllStudents());
    }

    public ProfileDto getMyProfile(Principal principal) {
        return userMapper.toProfileDto(userRepository.findUserByUsername(principal.getName()).get());
    }

    public List<StudentTaskPerformanceDto> getStudentTaskPerformances(Integer courseId) {
        List<StudentTaskPerformanceDto> students = userRepository.findStudentsByCourseId(courseId);
        List<TaskPerformanceDetailsDto> taskPerformances = userRepository.findTaskPerformancesByCourseId(courseId);

        Map<Integer, StudentTaskPerformanceDto> studentMap = students.stream()
                .collect(Collectors.toMap(StudentTaskPerformanceDto::getStudentId, Function.identity()));

        for (TaskPerformanceDetailsDto tp : taskPerformances) {
            StudentTaskPerformanceDto student = studentMap.get(tp.getStudentId());
            if (student != null) {
                student.addTaskPerformance(tp);
            }
        }

        return new ArrayList<>(studentMap.values());
    }

    public List<TaskPerformanceDto> getTaskPerformancesByStudentId(Integer studentId) {
        return taskPerformanceMapper.toDtoList(taskPerformanceRepository.findByDoneById(studentId));
    }

    public void updateCourse(CourseDto newCourseDto, int id) {
        Course exsistCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Course update = courseMapper.toEntity(newCourseDto);
        courseMapper.update(exsistCourse, update);
        courseRepository.save(exsistCourse);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public void updateTheme(ThemeDto newThemeDto, int id) {
        Theme exsistTheme = themeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Theme update = themeMapper.toEntity(newThemeDto);
        themeMapper.update(exsistTheme, update);
        themeRepository.save(exsistTheme);
    }

    public void deleteTheme(int id) {
        themeRepository.deleteById(id);
    }

    public void updateEdMaterial(EducationalMaterialDto newEducationalMaterialDto, int id) {
        EducationalMaterial educationalMaterial = educationalMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        EducationalMaterial update = educationalMaterialMapper.toEntity(newEducationalMaterialDto);
        educationalMaterialMapper.update(educationalMaterial, update);
        educationalMaterialRepository.save(educationalMaterial);
    }

    public void deleteEdMaterial(int id) {
        educationalMaterialRepository.deleteById(id);
    }


}
