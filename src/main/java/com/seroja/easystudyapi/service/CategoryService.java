package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.entity.Category;
import com.seroja.easystudyapi.mapper.CategoryMapper;
import com.seroja.easystudyapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryDto save(CategoryDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public CategoryDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<CategoryDto> listAll() {
        List<Category> categories = repository.findAll();

        return mapper.toDtoList(categories);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(CategoryDto dto, int id) {
        Category existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        Category updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }
}
