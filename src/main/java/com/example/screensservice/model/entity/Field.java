package com.example.screensservice.model.entity;

import com.example.screensservice.enums.ScreenTypeEnum;
import com.example.screensservice.enums.YesNoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "screen_fields")
public class Field {

    @Id
    @Column(name = "SF_ID")
    @GeneratedValue
    private BigDecimal id;

    @Column(name = "SF_DEFAULT_VALUE")
    private String defaultValue;

    @Column(name = "SF_NAME")
    private String name;

    @Column(name = "SF_MIN")
    private Integer min;

    @Column(name = "SF_IS_MANDATORY")
    @Enumerated(EnumType.STRING)
    private YesNoEnum isMandatory;

    @Column(name = "SF_LABEL")
    private String label;

    @Column(name = "SF_MAX")
    private Integer max;

    @Column(name = "SF_PLACEHOLDER")
    private String placeholder;

    @Column(name = "SF_TOOL_TIP")
    private String toolTip;

    @Column(name = "SF_TYPE")
    @Enumerated(EnumType.STRING)
    private ScreenTypeEnum type;

    @Column( name = "SF_SCREEN_ID")
    private BigDecimal screenId;

    @Column(name = "SF_ENABLED")
    @Enumerated(EnumType.STRING)
    private YesNoEnum isEnabled;

    @Column(name = "SF_HIDDEN")
    @Enumerated(EnumType.STRING)
    private YesNoEnum isHidden;

    @Column(name = "SF_READ_ONLY")
    @Enumerated(EnumType.STRING)
    private YesNoEnum isReadOnly;
}
