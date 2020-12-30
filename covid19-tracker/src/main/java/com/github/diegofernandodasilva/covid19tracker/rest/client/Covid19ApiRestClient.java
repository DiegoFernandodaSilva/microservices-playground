package com.github.diegofernandodasilva.covid19tracker.rest.client;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;

import java.util.List;

public interface Covid19ApiRestClient {

    /**
     * Http request to a external API and fetch covid19 countries statistics.
     * @return list of {@link CountryCovid19Statistics}
     */
    List<CountryCovid19Statistics> getCountriesCovid19Statistics();
}
