package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.CertificateDto;
import com.seroja.easystudyapi.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(source = "application.id", target = "applicationId")
    @Mapping(source = "application.student.id", target = "studentId")
    CertificateDto toDto(Certificate certificate);

    @Mapping(source = "applicationId", target = "application.id")
    @Mapping(source = "studentId", target = "application.student.id")
    Certificate toEntity(CertificateDto certificateDto);

    List<CertificateDto> toDtoList(List<Certificate> certificates);

    List<Certificate> toEntityList(List<CertificateDto> certificateDtos);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Certificate entity, Certificate updateCertificate);
}
