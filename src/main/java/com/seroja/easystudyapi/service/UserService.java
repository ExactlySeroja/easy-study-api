package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.dto.query.GetCoursesRequestDto;
import com.seroja.easystudyapi.entity.AppUser;
import com.seroja.easystudyapi.entity.Course;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import com.seroja.easystudyapi.specification.CourseSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final CourseSpecification courseSpecification;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    private final EducationalMaterialRepository educationalMaterialRepository;
    private final EducationalMaterialMapper educationalMaterialMapper;

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public UserDto getDtoByUsername(String username) {
        AppUser appUser = repository.findUserByUsername(username).get();
        return mapper.toDto(appUser);
    }

    public CourseDto getCourseById(int id) {
        return courseMapper.toDto(courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Course was not found!")));
    }

    public List<GetCoursesRequestDto> filterCourses(
            String courseName, Integer categoryId, Integer minPrice, Integer maxPrice, LocalDate minDate, LocalDate maxDate,
            String priceSort, String startDateSort, String endDateSort, int limit, int offset, Principal principal) {

        Pageable pageable = PageRequest.of(offset / limit, limit, courseSpecification.getSort(priceSort, startDateSort, endDateSort));

        if (courseName == null && categoryId == null && minPrice == null && maxPrice == null && minDate == null && maxDate == null
                && priceSort == null && startDateSort == null && endDateSort == null) {
            return courseMapper.toGetCoursesRequestDtoList(courseRepository.findAll(pageable).getContent());
        }

        Specification<Course> spec = Specification.where(courseSpecification.hasName(courseName))
                .and(courseSpecification.hasCategoryId(categoryId))
                .and(courseSpecification.hasMinPrice(minPrice))
                .and(courseSpecification.hasMaxPrice(maxPrice))
                .and(courseSpecification.hasMinDate(minDate))
                .and(courseSpecification.hasMaxDate(maxDate));

        List<GetCoursesRequestDto> courses = courseMapper.toGetCoursesRequestDtoList(courseRepository.findAll(spec, pageable).getContent());

        if (repository.findUserByUsername(principal.getName()).get().getRole().equals(Set.of("STUDENT"))) {
            for (GetCoursesRequestDto course : courses) {
                course.setApplication(applicationMapper
                        .toDto(applicationRepository
                                .findByStudentIdAndCourseId(getId(principal), course.getId())));
            }
        }

        return courses;
    }

    private Integer getId(Principal principal) {
        return repository.findUserByUsername(principal.getName()).get().getId();
    }

    public List<GetCoursesRequestDto> getGetCoursesRequestDtos(String priceSort, String startDateSort, String endDateSort, Principal principal, Specification<Course> spec, Pageable pageable) {
        List<GetCoursesRequestDto> courses = courseMapper.toGetCoursesRequestDtoList(courseRepository.findByAppUserId(spec,
                courseSpecification.getSort(priceSort, startDateSort, endDateSort), pageable));

        if (repository.findUserByUsername(principal.getName()).get().getRole().equals(Set.of("STUDENT"))) {
            for (GetCoursesRequestDto course : courses) {
                course.setApplication(applicationMapper
                        .toDto(applicationRepository
                                .findByStudentIdAndCourseId(getId(principal), course.getId())));
            }
        }
        return courses;
    }

    public List<ThemeDto> getAllThemesByCourseId(int courseId) {
        return themeMapper.toDtoList(themeRepository.findByCourseId(courseId));
    }

    public List<EdMaterialAndTaskPerformanceQueryDto> getAllEducationalMaterialsByTheme(int id) {
        List<EducationalMaterial> educationalMaterials = educationalMaterialRepository.findEducationalMaterialByThemeId(id);
        return educationalMaterialMapper.toQueryDtoList(educationalMaterials);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("AppUser with " + username + " not found")
        ));
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole().toArray(new String[0])).build();
    }

    public ResponseEntity<?> registerUser(UserDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            return new ResponseEntity<>("User with username " + dto.getUsername() + " already exists", HttpStatus.OK);
        }
        AppUser appUser = mapper.toEntity(dto);
        if (appUser.getRole().equals(Set.of("STUDENT"))) {
            appUser.setRole(Set.of("STUDENT"));
        } else if (appUser.getRole().equals(Set.of("TEACHER"))) {
            appUser.setRole(Set.of("TEACHER"));
        } else return new ResponseEntity<>("Invalid role", HttpStatus.BAD_REQUEST);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        repository.save(appUser);
        return new ResponseEntity<>("Successfully registration!", HttpStatus.OK);
    }

    public ResponseEntity<?> checkUserEmail(String email) {
        if (repository.existsByEmail(email)) {
            return new ResponseEntity<>("User with email " + email + " already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<?> checkUserPhoneNumber(String phoneNumber) {
        if (repository.existsByPhoneNumber(phoneNumber)) {
            return new ResponseEntity<>("User with phone number " + phoneNumber + " already exists", HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(HttpStatus.OK);

    }

    public ResponseEntity<?> checkUserUsername(String username) {
        if (repository.existsByUsername(username)) {
            return new ResponseEntity<>("User with username " + username + " already exists", HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(HttpStatus.OK);
    }

}
