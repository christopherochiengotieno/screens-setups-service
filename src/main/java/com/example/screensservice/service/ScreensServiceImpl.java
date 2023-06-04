package com.example.screensservice.service;

import com.example.screensservice.mapper.FieldMapper;
import com.example.screensservice.mapper.ScreenMapper;
import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.ScreenDto;
import com.example.screensservice.model.entity.Field;
import com.example.screensservice.model.entity.Screen;
import com.example.screensservice.repository.FieldRepository;
import com.example.screensservice.repository.ScreensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreensServiceImpl implements ScreensService {

    private final ScreensRepository screensRepository;
    private final ScreenMapper screenMapper;
    private final FieldMapper fieldMapper;
    private final FieldRepository fieldRepository;

    @Override
    public List<ScreenDto> getListOfFrontendScreens() {
        return screensRepository.findAll().stream().map(screenMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ScreenDto saveScreen(ScreenDto screenDto) {
        Screen mappedScreen = screenMapper.toEntity(screenDto);
        Screen savedScreen = screensRepository.save(mappedScreen);

        List<FieldDto> fields = screenDto.getFields();
        if (fields != null)
            fields.forEach(fieldDto -> {
                Field field = fieldMapper.toEntity(fieldDto);
                field.setScreenId(savedScreen.getId());
                fieldRepository.save(field);
            });
        return screenMapper.toDto(savedScreen);
    }

    @Override
    public ScreenDto getScreenById(BigDecimal screenId) {
        Screen screen = screensRepository.findById(screenId).orElseThrow(() -> new RuntimeException("Screen does not exist"));
        return screenMapper.toDto(screen);
    }
}
