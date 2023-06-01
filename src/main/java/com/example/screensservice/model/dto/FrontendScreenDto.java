package com.example.frontendscreensservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FrontendScreenDto {
    private BigDecimal id;
    private String screenName;
    private BigDecimal targetSystem;
    private String screenFields;
}
