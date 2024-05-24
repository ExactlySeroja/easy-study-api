package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.EducationalMaterialDto;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import com.seroja.easystudyapi.mapper.EducationalMaterialMapper;
import com.seroja.easystudyapi.repository.EducationalMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationalMaterialService {

    private final EducationalMaterialRepository repository;
    private final EducationalMaterialMapper mapper;

    public EducationalMaterialDto save(EducationalMaterialDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public EducationalMaterialDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<EducationalMaterialDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(EducationalMaterialDto dto, int id) {
        EducationalMaterial existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        EducationalMaterial updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }

}
