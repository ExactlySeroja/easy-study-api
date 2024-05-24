package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.ApplicationDto;
import com.seroja.easystudyapi.entity.Application;
import com.seroja.easystudyapi.mapper.ApplicationMapper;
import com.seroja.easystudyapi.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;

    public ApplicationDto save(ApplicationDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ApplicationDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<ApplicationDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(ApplicationDto dto, int id) {
        Application existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        Application updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }
}
