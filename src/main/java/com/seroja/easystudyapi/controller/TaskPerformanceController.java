package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import com.seroja.easystudyapi.service.TaskPerformanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class TaskPerformanceController {

    private final TaskPerformanceService service;

    @GetMapping(Routes.TEACHER_TASK_PERFORMANCE_BY_ID)
    public ResponseEntity<TaskPerformanceDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_TASK_PERFORMANCE)
    public List<TaskPerformanceDto> list() {
        return service.listAll();
    }

    @PostMapping(Routes.STUDENT_ADD_TASK_PERFORMANCE)
    public ResponseEntity<?> add(@RequestBody @Valid TaskPerformanceDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_TASK_PERFORMANCE_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid TaskPerformanceDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_TASK_PERFORMANCE_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
