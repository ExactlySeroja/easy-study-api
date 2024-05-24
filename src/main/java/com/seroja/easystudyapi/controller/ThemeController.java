package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.service.ThemeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService service;

    @GetMapping(Routes.TEACHER_THEME_BY_ID)
    public ResponseEntity<ThemeDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_THEMES)
    public List<ThemeDto> list() {
        return service.listAll();
    }

    @PostMapping(Routes.TEACHER_ADD_THEME)
    public ResponseEntity<?> add(@RequestBody @Valid ThemeDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_THEME_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid ThemeDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_THEME_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
