package com.example.screensservice.model.entity;

import com.example.screensservice.enums.YesNoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "select_options")
public class SelectOption {

    @Id
    @GeneratedValue
    @Column(name = "SO_ID")
    private BigDecimal id;

    @Column(name = "SO_VALUE")
    private String value;

    @Column(name = "SO_TEXT")
    private String text;

    @Column(name = "SO_SELECTED")
    private YesNoEnum isSelected;

    @Column(name = "SO_HIDDEN")
    private YesNoEnum isHidden;

    @Column(name = "SO_ENABLED")
    private YesNoEnum isEnabled;
}
