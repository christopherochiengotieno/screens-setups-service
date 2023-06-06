package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.entity.Field;
import org.springframework.stereotype.Component;

@Component
public class FieldMapperImpl implements FieldMapper {
    @Override
    public FieldDto toDto(Field field) {
        return FieldDto.builder()
                .min(field.getMin())
                .screenId(field.getScreenId())
                .defaultValue(field.getDefaultValue())
                .id(field.getId())
                .isMandatory(field.getIsMandatory())
                .label(field.getLabel())
                .max(field.getMax())
                .name(field.getName())
                .placeholder(field.getPlaceholder())
                .toolTip(field.getToolTip())
                .type(field.getType())
                .isEnabled(field.getIsEnabled())
                .isHidden(field.getIsHidden())
                .isReadOnly(field.getIsReadOnly())
                .build();
    }

    @Override
    public Field toEntity(FieldDto fieldDto) {
        return Field.builder()
                .min(fieldDto.getMin())
                .screenId(fieldDto.getScreenId())
                .defaultValue(fieldDto.getDefaultValue())
                .id(fieldDto.getId())
                .isMandatory(fieldDto.getIsMandatory())
                .label(fieldDto.getLabel())
                .max(fieldDto.getMax())
                .name(fieldDto.getName())
                .placeholder(fieldDto.getPlaceholder())
                .toolTip(fieldDto.getToolTip())
                .type(fieldDto.getType())
                .isEnabled(fieldDto.getIsEnabled())
                .isHidden(fieldDto.getIsHidden())
                .isReadOnly(fieldDto.getIsReadOnly())
                .build();
    }
}
