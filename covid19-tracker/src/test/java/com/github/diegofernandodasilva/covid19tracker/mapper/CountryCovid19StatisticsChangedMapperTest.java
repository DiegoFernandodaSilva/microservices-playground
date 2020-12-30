package com.github.diegofernandodasilva.covid19tracker.mapper;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.*;
import com.github.diegofernandodasilva.microservices.playground.covid19tracker.CountryCovid19StatisticsChanged;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CountryCovid19StatisticsChangedMapperTest {

    @Test
    public void mapToChangeEventTest() {
        final CountryCovid19Statistics entity = new CountryCovid19Statistics(1L,
                new Country(1L, "KoKoKo", new CountryEntryCode("KK", "KKK"), new Location(10.0F, 11.04F)),
                new Covid19Statistics(100L, 1L, 50L),
                Instant.now());

        CountryCovid19StatisticsChanged event = CountryCovid19StatisticsChangedMapper.INSTANCE.map(entity);

        assertEquals(entity.getCountry().getCountryRegion(), event.getCountryRegion());
        assertEquals(entity.getStatistics().getConfirmed(), event.getConfirmed());
        assertEquals(entity.getStatistics().getRecovered(), event.getRecovered());
        assertEquals(entity.getStatistics().getDeaths(), event.getDeaths());
        assertEquals(entity.getLastUpdated().toEpochMilli(), event.getLastUpdated());
    }
}
