package com.example.frontendscreensservice.mapper;

import com.example.frontendscreensservice.model.dto.FrontendScreenDto;
import com.example.frontendscreensservice.model.entity.FrontendScreen;

public interface FrontendScreensMapper {
    FrontendScreenDto toDto(FrontendScreen frontendScreen);
}
