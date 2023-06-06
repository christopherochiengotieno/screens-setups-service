package com.example.screensservice.service;

import com.example.screensservice.mapper.FieldMapper;
import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.GenericResponse;
import com.example.screensservice.model.entity.Field;
import com.example.screensservice.repository.FieldRepository;
import com.example.screensservice.repository.ScreensRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScreensFieldsServiceImpl implements ScreensFieldsService {

    private final ScreensRepository screensRepository;
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;

    @Override
    public List<FieldDto> getScreenFields(BigDecimal screenId) {
        return fieldRepository.findAllByScreenId(screenId).stream().map(fieldMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GenericResponse addFieldToScreen(BigDecimal screenId, List<FieldDto> fields) {
        if (screensRepository.existsById(screenId)) {
            fields.forEach(fieldDto -> {
                Field field = fieldMapper.toEntity(fieldDto);
                field.setScreenId(screenId);
                fieldRepository.save(field);
            });
        } else throw new RuntimeException("Screen does not exist");
        return GenericResponse.builder().message("Field(s) added successfully").build();
    }

    @Override
    public FieldDto getFieldById(BigDecimal fieldId) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new RuntimeException("Field does not exist"));
        return fieldMapper.toDto(field);
    }

    @Override
    public Boolean deleteFieldById(BigDecimal fieldId) {
        try {
            fieldRepository.deleteById(fieldId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public GenericResponse updateFields(List<FieldDto> fields) {

        if (fields != null) {
            fields.forEach(field -> {
                if(!fieldRepository.existsById(field.getId())) {
                    new RuntimeException("Field could not be found using the id provided: " + field.getId());
                } ;
                fieldRepository.save(fieldMapper.toEntity(field));
            });
        } else {
            throw new RuntimeException("Error, you provided an empty list of fields");
        }
        return GenericResponse.builder().message("Field(s) updated successfully").build();
    }
}
