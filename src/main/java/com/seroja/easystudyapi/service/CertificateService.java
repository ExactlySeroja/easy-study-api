package com.seroja.easystudyapi.service;

import com.seroja.easystudyapi.dto.CertificateDto;
import com.seroja.easystudyapi.entity.Certificate;
import com.seroja.easystudyapi.mapper.CertificateMapper;
import com.seroja.easystudyapi.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository repository;
    private final CertificateMapper mapper;

    public CertificateDto save(CertificateDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public CertificateDto getDto(int id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "CPU was not found!")));
    }

    public List<CertificateDto> listAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(CertificateDto dto, int id) {
        Certificate existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        Certificate updatedEntity = mapper.toEntity(dto);
        mapper.update(existingEntity, updatedEntity);
        repository.save(existingEntity);
    }

}
