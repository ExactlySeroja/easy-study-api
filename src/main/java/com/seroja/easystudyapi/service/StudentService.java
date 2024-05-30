package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.*;
import com.seroja.easystudyapi.dto.query.ApplicationWithFullInfoDto;
import com.seroja.easystudyapi.dto.query.GetCoursesRequestDto;
import com.seroja.easystudyapi.entity.Application;
import com.seroja.easystudyapi.entity.Certificate;
import com.seroja.easystudyapi.entity.Course;
import com.seroja.easystudyapi.mapper.ApplicationMapper;
import com.seroja.easystudyapi.mapper.CertificateMapper;
import com.seroja.easystudyapi.mapper.EducationalMaterialMapper;
import com.seroja.easystudyapi.mapper.TaskPerformanceMapper;
import com.seroja.easystudyapi.repository.*;
import com.seroja.easystudyapi.specification.CourseSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseSpecification courseSpecification;
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final EducationalMaterialRepository educationalMaterialRepository;
    private final EducationalMaterialMapper educationalMaterialMapper;
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private final UserRepository userRepository;
    private final TaskPerformanceRepository taskPerformanceRepository;
    private final TaskPerformanceMapper taskPerformanceMapper;
    private final UserService userService;

    public List<ApplicationWithFullInfoDto> getAllMyApplications(Principal principal) {
        int userId = getId(principal);
        List<Application> applications = applicationRepository.findAllByStudentId(userId);
        return applicationMapper.toWithFullInfoDtoList(applications);
    }

    public List<CertificateDto> getAllMyCertificates(Principal principal) {
        int userId = getId(principal);
        List<Certificate> certificates = certificateRepository.findCertificateByApplicationStudentId(userId);
        return certificateMapper.toDtoList(certificates);
    }

    public EducationalMaterialDto getEducationalMaterialById(int id) {
        return educationalMaterialMapper.toDto(educationalMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Material was not found!")));
    }

    public Optional<TaskPerformanceDto> getTaskPerformanceByMaterialId(Integer id, Principal principal) {
        return Optional.ofNullable(taskPerformanceMapper.toDto(taskPerformanceRepository.findByEdMaterialIdAndDoneById(id, getId(principal))
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Performance was not found!"))));
    }

    @Transactional
    public ApplicationDto createApplication(CreateApplicationRequestDto createApplicationRequestDto, Principal principal) {
        ApplicationDto applicationDto = new ApplicationDto();
        int userId = getId(principal);
        applicationDto.setStudentId(userId);
        applicationDto.setCourseId(createApplicationRequestDto.getCourseId());
        applicationDto.setDateOfCreation(LocalDate.now());
        applicationDto.setApplicationStatus(false);
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public TaskPerformanceDto createTaskPerformance(TaskPerformanceDto taskPerformanceDto) {
        return taskPerformanceMapper.toDto(taskPerformanceRepository.save(taskPerformanceMapper.toEntity(taskPerformanceDto)));
    }

    public List<GetCoursesRequestDto> filterCourses(
            String courseName, Integer categoryId, Integer minPrice, Integer maxPrice, LocalDate minDate, LocalDate maxDate,
            String priceSort, String startDateSort, String endDateSort, int limit, int offset, Principal principal) {
        Pageable pageable = PageRequest.of(offset / limit, limit, courseSpecification.getSort(priceSort, startDateSort, endDateSort));
        Specification<Course> spec = Specification.where(courseSpecification.hasStudentId(getId(principal)))
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
}
