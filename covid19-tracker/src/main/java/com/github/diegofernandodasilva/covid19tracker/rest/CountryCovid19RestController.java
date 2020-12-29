package com.github.diegofernandodasilva.covid19tracker.rest;

import com.github.diegofernandodasilva.covid19tracker.mapper.CountryCovid19StatisticsMapper;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryCovid19StatisticsDTO;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsService;
import com.github.diegofernandodasilva.covid19tracker.service.impl.Covid19StatisticsTrackerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.github.diegofernandodasilva.covid19tracker.rest.CountryCovid19RestController.COUNTRY_COVID19_API;

@RestController
@RequestMapping(COUNTRY_COVID19_API)
public class CountryCovid19RestController {

    public static final String COUNTRY_COVID19_API = "/api/covid19-statistics/countries";

    @Autowired
    private CountryCovid19StatisticsService service;
    @Autowired
    private Covid19StatisticsTrackerServiceImpl covid19StatisticsTrackerServiceImpl;

    @GetMapping
    public ResponseEntity<Page<CountryCovid19StatisticsDTO>> getCountryStatistics(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable).map(CountryCovid19StatisticsMapper.INSTANCE::map));
    }

    @PostMapping
    public ResponseEntity<Void> synchronizeData() {
        covid19StatisticsTrackerServiceImpl.synchronizeData();
        return ResponseEntity.ok().build();
    }
}
