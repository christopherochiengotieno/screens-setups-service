package com.example.screensservice.model.dto;

import com.example.screensservice.enums.YesNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SelectOptionDto {
    private BigDecimal id;
    private String value;
    private String text;
    private YesNoEnum isSelected;
    private YesNoEnum isHidden;
    private YesNoEnum isEnabled;
}
