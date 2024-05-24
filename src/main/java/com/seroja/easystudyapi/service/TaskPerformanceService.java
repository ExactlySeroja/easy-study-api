package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import com.seroja.easystudyapi.entity.TaskPerformance;
import com.seroja.easystudyapi.mapper.TaskPerformanceMapper;
import com.seroja.easystudyapi.repository.TaskPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskPerformanceService {

    private final TaskPerformanceRepository repository;
    private final TaskPerformanceMapper mapper;

    public TaskPerformanceDto save(TaskPerformanceDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public TaskPerformanceDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<TaskPerformanceDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(TaskPerformanceDto dto, int id) {
        TaskPerformance existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        TaskPerformance updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }

}
