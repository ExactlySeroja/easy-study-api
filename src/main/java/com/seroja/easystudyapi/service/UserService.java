package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.JwtRequest;
import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.entity.AppUser;
import com.seroja.easystudyapi.mapper.UserMapper;
import com.seroja.easystudyapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto save(UserDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public UserDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<UserDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(UserDto dto, int id) {
        AppUser existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        AppUser updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
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
        if(appUser.getRole().equals("STUDENT")) {
            appUser.setRole(Set.of("STUDENT"));
        } else if (appUser.getRole().equals("TEACHER")) {
            appUser.setRole(Set.of("TEACHER"));
        } else return new ResponseEntity<>("Invalid role", HttpStatus.BAD_REQUEST);
        repository.save(appUser);
        return new ResponseEntity<>("Successfully registration!", HttpStatus.OK);
    }

    public Optional<AppUser> findAppUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
