package com.example.frontendscreensservice.controller;

import com.example.frontendscreensservice.model.dto.FrontendScreenDto;
import com.example.frontendscreensservice.service.FrontendScreensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/frontend-screens")
public class FrontendScreensController {

    private final FrontendScreensService frontendScreensService;

    @GetMapping
    public ResponseEntity<List<FrontendScreenDto>> getFrontendScreens() {
        return ResponseEntity.ok().body(frontendScreensService.getListOfFrontendScreens());
    }
}
