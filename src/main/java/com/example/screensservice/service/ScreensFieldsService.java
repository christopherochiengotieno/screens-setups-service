package com.example.screensservice.service;

import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.GenericResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ScreensFieldsService {
    List<FieldDto> getScreenFields(BigDecimal screenId);

    GenericResponse addFieldToScreen(BigDecimal screenId, List<FieldDto> fields);

    FieldDto getFieldById(BigDecimal fieldId);

    Boolean deleteFieldById(BigDecimal fieldId);

    GenericResponse updateFields(List<FieldDto> fields);
}
