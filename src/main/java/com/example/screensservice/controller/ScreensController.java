package com.example.screensservice.controller;

import com.example.screensservice.model.dto.ScreenDto;
import com.example.screensservice.service.ScreensService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/screens")
@CrossOrigin(origins = "*")
@Slf4j
public class ScreensController {

    private final ScreensService screensService;

    @GetMapping
    public ResponseEntity<List<ScreenDto>> getFrontendScreens() {
        return ResponseEntity.ok().body(screensService.getListOfFrontendScreens());
    }

    @PostMapping
    public ResponseEntity<ScreenDto> createScreen(@RequestBody @Validated ScreenDto screenDto) {
        log.info("Screen to be saved" + screenDto.toString());
        ScreenDto savedScreen = screensService.saveScreen(screenDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + savedScreen.getId()).build().toUri();
        return ResponseEntity.created(location).body(savedScreen);
    }

    @GetMapping("/{screenId}")
    public ResponseEntity<ScreenDto> getScreenById(@PathVariable BigDecimal screenId) {
        return ResponseEntity.ok(screensService.getScreenById(screenId));
    }
}
