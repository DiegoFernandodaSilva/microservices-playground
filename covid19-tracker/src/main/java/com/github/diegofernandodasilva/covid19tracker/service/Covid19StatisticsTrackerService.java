package com.github.diegofernandodasilva.covid19tracker.service;

public interface Covid19StatisticsTrackerService {

    /**
     * Method responsible for synchronize data from external source to db.
     */
    void synchronizeData();
}
