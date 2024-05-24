package com.seroja.easystudyapi.mapper;

import com.seroja.easystudyapi.dto.EducationalMaterialDto;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationalMaterialMapper {

    EducationalMaterialDto toDto(EducationalMaterial educationalMaterial);

    EducationalMaterial toEntity(EducationalMaterialDto educationalMaterialDto);

    List<EducationalMaterialDto> toDtoList(List<EducationalMaterial> educationalMaterialList);

    List<EducationalMaterial> toEntityList(List<EducationalMaterialDto> educationalMaterialDtoList);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget EducationalMaterial entity, EducationalMaterial updatedEducationalMaterial);

}
