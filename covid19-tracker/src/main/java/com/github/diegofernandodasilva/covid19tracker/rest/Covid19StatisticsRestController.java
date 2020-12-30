package com.github.diegofernandodasilva.covid19tracker.rest;

import com.github.diegofernandodasilva.covid19tracker.service.Covid19StatisticsTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.diegofernandodasilva.covid19tracker.rest.Covid19StatisticsAPI.COVID19_API;

@RestController
@RequestMapping(COVID19_API)
public class Covid19StatisticsRestController {

    @Autowired
    private Covid19StatisticsTrackerService covid19StatisticsTrackerService;

    @PostMapping("/synchronizeData")
    public ResponseEntity<Void> synchronizeData() {
        covid19StatisticsTrackerService.synchronizeData();
        return ResponseEntity.ok().build();
    }
}
