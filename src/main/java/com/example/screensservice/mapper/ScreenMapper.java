package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.ScreenDto;
import com.example.screensservice.model.entity.Screen;

public interface ScreenMapper {
    ScreenDto toDto(Screen screen);

    Screen toEntity(ScreenDto screenDto);
}
