package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.entity.Theme;
import com.seroja.easystudyapi.mapper.ThemeMapper;
import com.seroja.easystudyapi.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository repository;
    private final ThemeMapper mapper;

    public ThemeDto save(ThemeDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ThemeDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<ThemeDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(ThemeDto dto, int id) {
        Theme existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        Theme updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }

}
