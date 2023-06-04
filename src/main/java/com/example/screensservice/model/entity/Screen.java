package com.example.screensservice.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "frontend_screens_register")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FSR_ID")
    private BigDecimal id;

    @Column(name = "FSR_SCREEN_NAME")
    private String screenName;

    @Column(name = "FSR_SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "FSR_DESCRIPTION")
    private String description;

    @Column(name = "FSR_TARGET_SYSTEM")
    private BigDecimal targetSystem;

    @Column(name = "FSR_SCREEN_FIELDS", columnDefinition = "json")
    private String screenFields;

    @Column(name = "FSR_VARIES")
    private String varies;

    @OneToMany
    @JoinColumn(referencedColumnName = "")
    private List<Field> fields;

}
