package com.example.screensservice.service;

import com.example.screensservice.enums.ScreenTypeEnum;
import com.example.screensservice.mapper.FieldMapper;
import com.example.screensservice.mapper.SelectOptionMapper;
import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.GenericResponse;
import com.example.screensservice.model.entity.Field;
import com.example.screensservice.model.entity.SelectOption;
import com.example.screensservice.model.entity.SelectOptionPerField;
import com.example.screensservice.model.entity.SelectOptionPerFieldKey;
import com.example.screensservice.repository.FieldRepository;
import com.example.screensservice.repository.ScreensRepository;
import com.example.screensservice.repository.SelectOptionPerFieldRepository;
import com.example.screensservice.repository.SelectOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScreensFieldsServiceImpl implements ScreensFieldsService {

    private final ScreensRepository screensRepository;
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final SelectOptionRepository selectOptionRepository;
    private final SelectOptionMapper selectOptionMapper;
    private final SelectOptionPerFieldRepository selectOptionPerFieldRepository;

    @Override
    public List<FieldDto> getScreenFields(BigDecimal screenId) {
        return fieldRepository.findAllByScreenId(screenId).stream().map(fieldMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GenericResponse addFieldToScreen(BigDecimal screenId, List<FieldDto> fields) {
        if (screensRepository.existsById(screenId)) {
            fields.forEach(fieldDto -> {
                if (fieldDto.getType().equals(ScreenTypeEnum.select)) {
                    List<SelectOption> options = processSelectOptions(fieldDto);

                    // save the field
                    Field field = fieldMapper.toEntity(fieldDto);
                    field.setScreenId(screenId);
                    Field savedField = fieldRepository.save(field);

                    // save the relationship between the field and the option
                    options.forEach(selectOption -> {
                        selectOptionPerFieldRepository.save(SelectOptionPerField.builder()
                                .id(SelectOptionPerFieldKey.builder().selectOptionId(selectOption.getId()).fieldId(savedField.getId()).build())
                                .build());
                    });


                } else {
                    Field field = fieldMapper.toEntity(fieldDto);
                    field.setScreenId(screenId);
                    fieldRepository.save(field);
                }
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

        System.out.println(fields);
        if (fields != null) {
            fields.forEach(field -> {
                if(!fieldRepository.existsById(field.getId())) {
                    new RuntimeException("Field could not be found using the id provided: " + field.getId());
                } ;

                if (field.getType().equals(ScreenTypeEnum.select)) {
                    List<SelectOption> options = processSelectOptions(field);

                    // save the field
                    Field savedField = fieldRepository.save(fieldMapper.toEntity(field));

                    // save the relationship between the field and the option
                    options.forEach(selectOption -> {
                        SelectOptionPerFieldKey id = SelectOptionPerFieldKey.builder().selectOptionId(selectOption.getId()).fieldId(savedField.getId()).build();
                        if (!selectOptionPerFieldRepository.existsById(id)) selectOptionPerFieldRepository.save(SelectOptionPerField.builder().id(id).build());
                    });
                } else {
                    fieldRepository.save(fieldMapper.toEntity(field));
                }
            });
        } else {
            throw new RuntimeException("Error, you provided an empty list of fields");
        }
        return GenericResponse.builder().message("Field(s) updated successfully").build();
    }

    private List<SelectOption> processSelectOptions(FieldDto field) {
        List<SelectOption> options = new ArrayList<>();

        // check if option exist else save it
        field.getSelectOptions().forEach(option -> {
            Optional<SelectOption> byValueAndText = selectOptionRepository.findByValueAndText(option.getValue(), option.getText());
            if (byValueAndText.isEmpty()) {
                log.info("Saving new select option");
                options.add(selectOptionRepository.save(selectOptionMapper.toEntity(option)));
            } else {
                log.info("Attaching existing select option");
                options.add(byValueAndText.get());
            }
        });
        return options;
    }
}
