package com.example.screensservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class SelectOptionPerFieldKey implements Serializable {

    @Column(name = "SOPF_SELECT_OPTION_ID")
    BigDecimal selectOptionId;

    @Column(name = "SOPF_FIELD_ID")
    BigDecimal fieldId;
}
