package com.github.diegofernandodasilva.covid19tracker.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CountryCovid19Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Country country;
    @Embedded
    private Covid19Statistics statistics;
    private Instant lastUpdated;
}
