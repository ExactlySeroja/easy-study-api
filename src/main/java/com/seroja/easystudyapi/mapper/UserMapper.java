package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.UserDto;
import com.seroja.easystudyapi.dto.query.ProfileDto;
import com.seroja.easystudyapi.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(AppUser appUser);

    AppUser toEntity(UserDto userDto);

    ProfileDto toProfileDto(AppUser appUser);

    List<UserDto> toDtoList(List<AppUser> appUsers);

    List<AppUser> toEntityList(List<UserDto> userDtos);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget AppUser entity, AppUser updatedEntity);
}
