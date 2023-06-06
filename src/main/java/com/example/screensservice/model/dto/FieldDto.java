package com.example.screensservice.model.dto;

import com.example.screensservice.enums.ScreenTypeEnum;
import com.example.screensservice.enums.YesNoEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class FieldDto {

    private BigDecimal id;
    private String defaultValue;
    private String name;
    private Integer min;
    private YesNoEnum isMandatory;
    private String label;
    private Integer max;
    private String placeholder;
    private String toolTip;
    private ScreenTypeEnum type;
    private BigDecimal screenId;
    private YesNoEnum isEnabled;
    private YesNoEnum isHidden;
    private YesNoEnum isReadOnly;
}
