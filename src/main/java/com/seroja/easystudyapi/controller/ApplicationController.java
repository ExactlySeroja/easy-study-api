package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.ApplicationDto;
import com.seroja.easystudyapi.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @GetMapping(value = Routes.TEACHER_CHECK_APPLICATION_BY_ID)
    public ResponseEntity<ApplicationDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = Routes.TEACHER_GET_ALL_APPLICATIONS)
    public List<ApplicationDto> list() {
        return service.listAll();
    }

    @PostMapping(value = Routes.TEACHER_ADD_APPLICATION)
    public ResponseEntity<?> add(@RequestBody @Valid ApplicationDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_CHECK_APPLICATION_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid ApplicationDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_CHECK_APPLICATION_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
