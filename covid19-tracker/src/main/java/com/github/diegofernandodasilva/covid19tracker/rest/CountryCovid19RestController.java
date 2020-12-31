package com.github.diegofernandodasilva.covid19tracker.rest;

import com.github.diegofernandodasilva.covid19tracker.mapper.CountryCovid19StatisticsMapper;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryCovid19StatisticsDTO;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static com.github.diegofernandodasilva.covid19tracker.rest.Covid19StatisticsAPI.COUNTRY_COVID19_API;

@RestController
@RequestMapping(COUNTRY_COVID19_API)
public class CountryCovid19RestController {

    @Autowired
    private CountryCovid19StatisticsService service;

    @RolesAllowed("USER")
    @GetMapping
    public ResponseEntity<Page<CountryCovid19StatisticsDTO>> getCountryStatistics(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable).map(CountryCovid19StatisticsMapper.INSTANCE::map));
    }

}
