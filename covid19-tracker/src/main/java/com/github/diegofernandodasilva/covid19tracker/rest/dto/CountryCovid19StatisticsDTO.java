package com.github.diegofernandodasilva.covid19tracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCovid19StatisticsDTO {

    @JsonProperty(value = "countryregion")
    private String countryRegion;
    private LocationDTO location;
    @JsonProperty(value = "lastupdate")
    private Instant lastUpdate;
    private Long confirmed;
    private Long deaths;
    private Long recovered;
    @JsonProperty("countrycode")
    private CountryEntryCodeDTO countryEntryCode;

}
