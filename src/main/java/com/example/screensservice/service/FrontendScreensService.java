package com.example.screensservice.service;

import com.example.screensservice.model.dto.FrontendScreenDto;

import java.util.List;

public interface FrontendScreensService {
    List<FrontendScreenDto> getListOfFrontendScreens();
}
