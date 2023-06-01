package com.example.frontendscreensservice.service;

import com.example.frontendscreensservice.model.dto.FrontendScreenDto;

import java.util.List;

public interface FrontendScreensService {
    List<FrontendScreenDto> getListOfFrontendScreens();
}
