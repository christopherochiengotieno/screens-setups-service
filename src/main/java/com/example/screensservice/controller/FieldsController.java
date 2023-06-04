package com.example.screensservice.controller;

import com.example.screensservice.model.dto.FieldDto;
import com.example.screensservice.model.dto.GenericResponse;
import com.example.screensservice.service.ScreensFieldsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/fields")
public class FieldsController {

    private final ScreensFieldsService screensFieldsService;

    @GetMapping
    public ResponseEntity<List<FieldDto>> getFieldsByScreenId(@RequestParam BigDecimal screenId) {
        return ResponseEntity.ok().body(screensFieldsService.getScreenFields(screenId));
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<FieldDto> getFieldByFieldId(@PathVariable BigDecimal fieldId) {
        return ResponseEntity.ok().body(screensFieldsService.getFieldById(fieldId));
    }

    @DeleteMapping("/{fieldId}")
    public ResponseEntity<GenericResponse> deleteFieldByFieldId(@PathVariable BigDecimal fieldId) {
        Boolean isDeleted = screensFieldsService.deleteFieldById(fieldId);
        if (isDeleted)
            return ResponseEntity.ok().body(GenericResponse.builder().message("Field successfully deleted").build());
        else return ResponseEntity.ok().body(GenericResponse.builder().message("Field failed to be deleted. Check logs for more details.").build());
    }

    @PostMapping
    public ResponseEntity<GenericResponse> addFieldsToScreen(@RequestParam BigDecimal screenId,
                                                     @RequestBody List<FieldDto> fields) {
        GenericResponse response = screensFieldsService.addFieldToScreen(screenId, fields);
        return ResponseEntity.ok().body(response);
    }
}
