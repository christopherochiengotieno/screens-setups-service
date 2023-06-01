package com.example.screensservice.model.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FrontendScreenDto {
    private BigDecimal id;
    private String screenName;
    private String shortDescription;
    private String description;
    private BigDecimal targetSystem;
    private Object screenFields;
}
