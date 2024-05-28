package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.entity.AppUser;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import com.seroja.easystudyapi.mapper.*;
import com.seroja.easystudyapi.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

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

    public void update(UserDto dto, int id) {
        AppUser existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        AppUser updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }

    public List<CourseDto> getAllCourses() {
        return courseMapper.toDtoList(courseRepository.findAll());
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

    public Optional<AppUser> findAppUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    public boolean checkUserEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean checkUserPhoneNumber(String phoneNumber){
        return repository.existsByPhoneNumber(phoneNumber);
    }

    public boolean checkUserUsername(String username){
        return repository.existsByUsername(username);
    }

}
