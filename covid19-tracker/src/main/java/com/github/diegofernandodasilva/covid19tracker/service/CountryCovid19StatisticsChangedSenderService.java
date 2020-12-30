package com.github.diegofernandodasilva.covid19tracker.service;

import com.github.diegofernandodasilva.microservices.playground.covid19tracker.CountryCovid19StatisticsChanged;

public interface CountryCovid19StatisticsChangedSenderService {

    /**
     * sends {@link CountryCovid19StatisticsChanged}
     * when covid19 statistics for a country has been updated;
     * @param event to be sent
     */
    void send(CountryCovid19StatisticsChanged event);

}
