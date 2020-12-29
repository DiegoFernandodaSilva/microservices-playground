package com.github.diegofernandodasilva.covid19tracker.service;

import com.github.diegofernandodasilva.microservices.playground.covid19tracker.CountryCovid19StatisticsChanged;

public interface CountryCovid19StatisticsChangedSenderService {

    void send(CountryCovid19StatisticsChanged event);

}
