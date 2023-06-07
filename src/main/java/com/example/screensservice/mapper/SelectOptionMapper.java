package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.SelectOptionDto;
import com.example.screensservice.model.entity.SelectOption;

public interface SelectOptionMapper {
    SelectOptionDto toDto(SelectOption selectOption);

    SelectOption toEntity(SelectOptionDto option);
}
