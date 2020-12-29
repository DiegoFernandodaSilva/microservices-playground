package com.github.diegofernandodasilva.covid19tracker.service.impl;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.rest.client.Covid19ApiRestClient;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsService;
import com.github.diegofernandodasilva.covid19tracker.service.CountryService;
import com.github.diegofernandodasilva.covid19tracker.service.Covid19StatisticsTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Covid19StatisticsTrackerServiceImpl implements Covid19StatisticsTrackerService {

    private Covid19ApiRestClient restClient;
    private CountryService countryService;
    private CountryCovid19StatisticsService countryCovid19StatisticsService;

    @Autowired
    public Covid19StatisticsTrackerServiceImpl(Covid19ApiRestClient restClient,
                                               CountryService countryService,
                                               CountryCovid19StatisticsService countryCovid19StatisticsService) {

        this.restClient = restClient;
        this.countryService = countryService;
        this.countryCovid19StatisticsService = countryCovid19StatisticsService;
    }

    @Override
    public void synchronizeData() {
        updateAllCountriesCovid19Statistics(restClient.getCountriesCovid19Statistics());
    }

    private void updateAllCountriesCovid19Statistics(List<CountryCovid19Statistics> countryCovid19Statistics) {
        for (final CountryCovid19Statistics covid19Statistics : countryCovid19Statistics) {
            final Optional<Country> country = countryService.findByCountryRegion(covid19Statistics.getCountry().getCountryRegion());
            if (country.isPresent()) {
                covid19Statistics.setCountry(country.get());
                updateCountryCovid19Statistics(covid19Statistics);
                return;
            }
            saveCountryCovid19Statistics(covid19Statistics);
        }
    }

    private void updateCountryCovid19Statistics(CountryCovid19Statistics newCovid19Statistics) {
        final CountryCovid19Statistics oldCovid19Statistics =
                countryCovid19StatisticsService.getByCountry(newCovid19Statistics.getCountry());

        if (oldCovid19Statistics.getLastUpdate().compareTo(newCovid19Statistics.getLastUpdate()) != 0) {
            countryCovid19StatisticsService.update(newCovid19Statistics);
        }
    }

    private void saveCountryCovid19Statistics(CountryCovid19Statistics covid19Statistics) {
        Country savedCountry = countryService.create(covid19Statistics.getCountry());
        covid19Statistics.setCountry(savedCountry);
        countryCovid19StatisticsService.create(covid19Statistics);
    }

}
