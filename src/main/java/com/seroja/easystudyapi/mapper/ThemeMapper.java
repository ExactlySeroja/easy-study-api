package com.seroja.easystudyapi.mapper;

import com.seroja.easystudyapi.dto.ThemeDto;
import com.seroja.easystudyapi.entity.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    ThemeDto toDto(Theme theme);

    Theme toEntity(ThemeDto themeDto);

    List<ThemeDto> toDtoList(List<Theme> themes);

    List<Theme> toEntityList(List<ThemeDto> themeDtos);

    @Mapping(source = "id", target = "id", ignore = true)
    void update(@MappingTarget Theme entity, Theme updateEntity);


}
