package com.github.diegofernandodasilva.covid19tracker.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntryCodeDTO {

    private String iso2;
    private String iso3;
}
