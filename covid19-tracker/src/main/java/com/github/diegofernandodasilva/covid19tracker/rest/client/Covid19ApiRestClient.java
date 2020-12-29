package com.github.diegofernandodasilva.covid19tracker.rest.client;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;

import java.util.List;

public interface Covid19ApiRestClient {

    /**
     * Http request for API responsible to provide covid19 country statistics.
     * @return {@link CountryCovid19Statistics}
     */
    List<CountryCovid19Statistics> getCountriesCovid19Statistics();
}
