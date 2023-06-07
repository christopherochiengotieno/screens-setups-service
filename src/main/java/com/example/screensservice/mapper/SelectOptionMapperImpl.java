package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.SelectOptionDto;
import com.example.screensservice.model.entity.SelectOption;
import org.springframework.stereotype.Component;

@Component
public class SelectOptionMapperImpl implements SelectOptionMapper {
    @Override
    public SelectOptionDto toDto(SelectOption selectOption) {
        return SelectOptionDto.builder()
                .isSelected(selectOption.getIsSelected())
                .value(selectOption.getValue())
                .text(selectOption.getText())
                .id(selectOption.getId())
                .isEnabled(selectOption.getIsEnabled())
                .isHidden(selectOption.getIsHidden())
                .build();
    }

    @Override
    public SelectOption toEntity(SelectOptionDto selectOption) {
        return SelectOption.builder()
                .isSelected(selectOption.getIsSelected())
                .value(selectOption.getValue())
                .text(selectOption.getText())
                .id(selectOption.getId())
                .isEnabled(selectOption.getIsEnabled())
                .isHidden(selectOption.getIsHidden())
                .build();
    }
}
