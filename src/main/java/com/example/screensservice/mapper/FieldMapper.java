package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.entity.Field;

public interface FieldMapper {
    FieldDto toDto(Field field);

    Field toEntity(FieldDto fieldDto);
}
