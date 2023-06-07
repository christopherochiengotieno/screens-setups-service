package com.example.screensservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "select_options_per_field")
public class SelectOptionPerField {

    @EmbeddedId
    private SelectOptionPerFieldKey id;

    /*@ManyToOne
    @JoinColumn(name = "SOPF_FIELD_ID")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "SOPF_SELECT_OPTION_ID")
    private SelectOption selectOption;*/
}
