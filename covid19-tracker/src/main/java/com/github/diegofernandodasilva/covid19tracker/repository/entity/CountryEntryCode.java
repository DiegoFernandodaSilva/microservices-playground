package com.github.diegofernandodasilva.covid19tracker.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntryCode {

    private String iso2;
    private String iso3;
}
