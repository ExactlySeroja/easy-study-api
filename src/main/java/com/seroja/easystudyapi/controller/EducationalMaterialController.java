package com.seroja.easystudyapi.controller;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.dto.EducationalMaterialDto;
import com.seroja.easystudyapi.service.EducationalMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class EducationalMaterialController {

    private final EducationalMaterialService service;

    @GetMapping(Routes.TEACHER_EDUCATIONAL_MATERIAL_BY_ID)
    public ResponseEntity<EducationalMaterialDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Routes.TEACHER_GET_ALL_EDUCATIONAL_MATERIAL)
    public List<EducationalMaterialDto> list() {
        return service.listAll();
    }

    @PostMapping(Routes.TEACHER_ADD_EDUCATIONAL_MATERIAL)
    public ResponseEntity<?> add(@RequestBody @Valid EducationalMaterialDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Routes.TEACHER_EDUCATIONAL_MATERIAL_BY_ID)
    public ResponseEntity<?> update(@RequestBody @Valid EducationalMaterialDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Routes.TEACHER_EDUCATIONAL_MATERIAL_BY_ID)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
