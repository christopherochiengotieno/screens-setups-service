package com.example.screensservice.mapper;

import com.example.screensservice.model.dto.FrontendScreenDto;
import com.example.screensservice.model.entity.FrontendScreen;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FrontendScreensMapper {
    FrontendScreenDto toDto(FrontendScreen frontendScreen);
}
