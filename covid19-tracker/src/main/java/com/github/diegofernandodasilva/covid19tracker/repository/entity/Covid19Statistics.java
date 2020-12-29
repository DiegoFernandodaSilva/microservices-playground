package com.github.diegofernandodasilva.covid19tracker.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Covid19Statistics {

    private Long confirmed;
    private Long deaths;
    private Long recovered;
}
