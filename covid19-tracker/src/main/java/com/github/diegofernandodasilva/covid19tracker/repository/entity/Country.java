package com.github.diegofernandodasilva.covid19tracker.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String countryRegion;
    @Embedded
    private CountryEntryCode countryEntryCode;
    @Embedded
    private Location location;
}
