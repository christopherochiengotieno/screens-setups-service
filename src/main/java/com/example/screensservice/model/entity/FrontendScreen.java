package com.example.frontendscreensservice.model.entity;


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
@Table(name = "FRONTEND_SCREENS_REGISTER", schema = "PITCHES")
public class FrontendScreen {

    @Id
    @GeneratedValue
    @Column(name = "FSR_ID")
    private BigDecimal id;

    @Column(name = "FSR_SCREEN_NAME")
    private String screenName;

    @Column(name = "FSR_TARGET_SYSTEM")
    private BigDecimal targetSystem;

    @Column(name = "FSR_SCREEN_FIELDS", columnDefinition = "json")
    private String screenFields;
}
