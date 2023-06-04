package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.ScreenDto;
import com.example.screensservice.model.entity.Field;
import com.example.screensservice.model.entity.Screen;
import com.example.screensservice.repository.FieldRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScreenMapperImpl implements ScreenMapper {

    private final ObjectMapper objectMapper;
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;

    @Override
    public ScreenDto toDto(Screen screen) {

        ScreenDto screenDto = null;
        try {
            screenDto = ScreenDto.builder()
                    .screenName(screen.getScreenName())
                    .targetSystem(screen.getTargetSystem())
                    .id(screen.getId())
                    .description(screen.getDescription())
                    .shortDescription(screen.getShortDescription())
                    .screenFields(objectMapper.readValue(screen.getScreenFields(), Object.class))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<FieldDto> fields = fieldRepository.findAllByScreenId(screen.getId()).stream().map(fieldMapper::toDto).toList();
        screenDto.setFields(fields);

        return screenDto;
    }

    @Override
    public Screen toEntity(ScreenDto screenDto) {
        Screen screen = null;
        try {
            screen = Screen.builder()
                    .screenName(screenDto.getScreenName())
                    .targetSystem(screenDto.getTargetSystem())
                    .id(screenDto.getId())
                    .description(screenDto.getDescription())
                    .shortDescription(screenDto.getShortDescription())
                    .screenFields(objectMapper.writeValueAsString(screenDto.getScreenFields()))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return screen;
    }
}
