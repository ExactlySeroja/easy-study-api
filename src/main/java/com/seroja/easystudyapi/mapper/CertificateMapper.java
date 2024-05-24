package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.CertificateDto;
import com.seroja.easystudyapi.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    CertificateDto toDto(Certificate certificate);

    Certificate toEntity(CertificateDto certificateDto);

    List<CertificateDto> toDtoList(List<Certificate> certificates);

    List<Certificate> toEntityList(List<CertificateDto> certificateDtos);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Certificate entity, Certificate updateCertificate);
}
