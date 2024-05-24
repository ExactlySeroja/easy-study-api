package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping(Routes.TEACHER_CATEGORY_BY_ID)
    public ResponseEntity<CategoryDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_CATEGORIES)
    public List<CategoryDto> list() {
        return service.listAll();
    }

    @PostMapping(Routes.TEACHER_ADD_CATEGORY)
    public ResponseEntity<?> add(@RequestBody @Valid CategoryDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_CATEGORY_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid CategoryDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_CATEGORY_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
