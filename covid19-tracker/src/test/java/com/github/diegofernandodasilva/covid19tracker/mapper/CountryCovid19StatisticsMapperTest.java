package com.github.diegofernandodasilva.covid19tracker.mapper;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.*;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryCovid19StatisticsDTO;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryEntryCodeDTO;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.LocationDTO;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CountryCovid19StatisticsMapperTest {

    @Test
    public void mapToDTO() {
        final CountryCovid19Statistics entity = new CountryCovid19Statistics(1L,
                new Country(1L, "KoKoKo", new CountryEntryCode("KK", "KKK"), new Location(10.0F, 11.04F)),
                new Covid19Statistics(100L, 1L, 50L),
                Instant.now());

        CountryCovid19StatisticsDTO dto = CountryCovid19StatisticsMapper.INSTANCE.map(entity);

        verifyMappedValues(entity, dto);
    }

    @Test
    public void mapToEntity() {
        final CountryCovid19StatisticsDTO dto = new CountryCovid19StatisticsDTO("KoKoKo",
                new LocationDTO(10.0F, 11.04F),
                Instant.now(),
                100L, 1L, 50L,
                new CountryEntryCodeDTO("KK", "KKK"));

        CountryCovid19Statistics entity = CountryCovid19StatisticsMapper.INSTANCE.map(dto);

        verifyMappedValues(entity, dto);
    }

    private void verifyMappedValues(CountryCovid19Statistics entity, CountryCovid19StatisticsDTO dto) {
        assertEquals(entity.getStatistics().getConfirmed(), dto.getConfirmed());
        assertEquals(entity.getStatistics().getDeaths(), dto.getDeaths());
        assertEquals(entity.getStatistics().getRecovered(), dto.getRecovered());
        assertEquals(entity.getCountry().getCountryEntryCode().getIso2(), dto.getCountryEntryCode().getIso2());
        assertEquals(entity.getCountry().getCountryEntryCode().getIso3(), dto.getCountryEntryCode().getIso3());
        assertEquals(entity.getCountry().getCountryRegion(), dto.getCountryRegion());
        assertEquals(entity.getCountry().getLocation().getLat(), dto.getLocation().getLat());
        assertEquals(entity.getCountry().getLocation().getLon(), dto.getLocation().getLng());
        assertEquals(entity.getLastUpdate(), dto.getLastUpdate());

    }
}
