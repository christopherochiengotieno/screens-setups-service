package com.example.screensservice.service;

import com.example.screensservice.model.dto.ScreenDto;

import java.math.BigDecimal;
import java.util.List;

public interface ScreensService {
    List<ScreenDto> getListOfFrontendScreens();

    ScreenDto saveScreen(ScreenDto screenDto);

    ScreenDto getScreenById(BigDecimal screenId);
}
