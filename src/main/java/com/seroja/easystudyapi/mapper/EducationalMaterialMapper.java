package com.seroja.easystudyapi.mapper;

import com.seroja.easystudyapi.dto.EducationalMaterialDto;
import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceQueryDto;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationalMaterialMapper {

    @Mapping(source = "ed_material_name", target = "name")
    @Mapping(source = "theme.id", target = "themeId")
    EducationalMaterialDto toDto(EducationalMaterial educationalMaterial);

    @Mapping(source = "name", target = "ed_material_name")
    @Mapping(source = "themeId", target = "theme.id")
    EducationalMaterial toEntity(EducationalMaterialDto educationalMaterialDto);

    @Mapping(source = "ed_material_name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dateOfUpload", target = "dateOfUpload")
    EdMaterialAndTaskPerformanceQueryDto toQueryDto(EducationalMaterial educationalMaterial);

    List<EducationalMaterialDto> toDtoList(List<EducationalMaterial> educationalMaterialList);

    List<EdMaterialAndTaskPerformanceQueryDto> toQueryDtoList(List<EducationalMaterial> educationalMaterialList);

    List<EducationalMaterial> toEntityList(List<EducationalMaterialDto> educationalMaterialDtoList);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget EducationalMaterial entity, EducationalMaterial updatedEducationalMaterial);

}
