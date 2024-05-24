package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.CategoryDto;
import com.seroja.easystudyapi.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "categoryId", target = "id")
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categoryList);

    List<Category> toCategoryList(List<CategoryDto> categoryDtoList);

    @Mapping(ignore = true, source = "categoryId", target = "categoryId")
    void update(@MappingTarget Category entity, Category updatedCategory);
}
