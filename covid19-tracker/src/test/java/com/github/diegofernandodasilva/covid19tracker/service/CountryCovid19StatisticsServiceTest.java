package com.github.diegofernandodasilva.covid19tracker.service;

import com.github.diegofernandodasilva.covid19tracker.exception.EntityAlreadyExistsException;
import com.github.diegofernandodasilva.covid19tracker.repository.CountryCovid19StatisticsRepository;
import com.github.diegofernandodasilva.covid19tracker.repository.CountryRepository;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountryCovid19StatisticsServiceTest {

    @Autowired
    private CountryCovid19StatisticsService countryCovid19StatisticsService;
    @Autowired
    private CountryCovid19StatisticsRepository repository;
    @Autowired
    private CountryRepository countryRepository;
    public Country country;

    @MockBean
    public CountryCovid19StatisticsChangedSenderService senderService;


    @BeforeEach
    void setup() {
        country = countryRepository.save(
                new Country(null, "COUNTRY_NAME",
                        new CountryEntryCode("KK", "KKK"),
                        new Location(10.0F, 10.0F)
                ));

    }

    @AfterEach()
    void tearDown() {
        Optional<CountryCovid19Statistics> entityToDelete = repository.findByCountry(country);
        if (entityToDelete.isPresent()) {
            repository.delete(entityToDelete.get());
        }
        countryRepository.deleteById(country.getId());
    }

    @Test
    public void createCountryCovid19StatisticsAlreadyExistsTest() {
        final long idToDelete = repository.save(countryCovid19Statistics()).getId();

        CountryCovid19Statistics entityToSave = countryCovid19Statistics();

        assertThrows(EntityAlreadyExistsException.class, () ->
                countryCovid19StatisticsService.create(entityToSave));

        repository.deleteById(idToDelete);
    }

    @Test
    public void createTest() {
        final CountryCovid19Statistics entityBeforeSave = countryCovid19Statistics();
        assertNull(entityBeforeSave.getId());

        CountryCovid19Statistics savedEntity = countryCovid19StatisticsService.create(entityBeforeSave);

        assertNotNull(savedEntity.getId());
        assertEquals(entityBeforeSave.getLastUpdated(), savedEntity.getLastUpdated());
        assertEquals(entityBeforeSave.getCountry().getCountryRegion(), savedEntity.getCountry().getCountryRegion());
    }

    @Test
    public void updateTest() {
        CountryCovid19Statistics entityBeforeUpdate = repository.save(countryCovid19Statistics());

        CountryCovid19Statistics updatedEntity = countryCovid19Statistics();
        updatedEntity.setStatistics(new Covid19Statistics(150L, 2L, 70L));
        updatedEntity.setLastUpdated(Instant.now());

        countryCovid19StatisticsService.update(updatedEntity);

        CountryCovid19Statistics entityAfterUpdate = repository.findById(entityBeforeUpdate.getId()).get();

        assertNotEquals(entityBeforeUpdate.getLastUpdated(), entityAfterUpdate.getLastUpdated());
        assertEquals(entityBeforeUpdate.getCountry().getCountryRegion(), entityAfterUpdate.getCountry().getCountryRegion());
    }

    private CountryCovid19Statistics countryCovid19Statistics() {
        return new CountryCovid19Statistics(null,
                country,
                new Covid19Statistics(100L, 1L, 50L),
                Instant.now());
    }
}
