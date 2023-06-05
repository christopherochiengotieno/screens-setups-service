package com.example.screensservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ScreenDto {
    private BigDecimal id;
    private String screenName;
    private String shortDescription;
    private String description;
    private BigDecimal targetSystem;
    private Object screenFields;
    private List<FieldDto> fields = new ArrayList<>();
}
