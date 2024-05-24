package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping(Routes.TEACHER_COURSE_BY_ID)
    public ResponseEntity<CourseDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_COURSES)
    public List<CourseDto> list() {
        return service.listAll();
    }

    @PostMapping(Routes.TEACHER_ADD_COURSE)
    public ResponseEntity<?> add(@RequestBody @Valid CourseDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_COURSE_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid CourseDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_COURSE_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
