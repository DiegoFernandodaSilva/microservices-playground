package com.github.diegofernandodasilva.covid19tracker.service;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryCovid19StatisticsService {

    CountryCovid19Statistics create(@NonNull CountryCovid19Statistics covid19Statistics);

    void update(@NonNull CountryCovid19Statistics countryCovid19Statistics);

    CountryCovid19Statistics getByCountry(@NonNull Country country);

    Optional<CountryCovid19Statistics> findByCountry(@NonNull Country country);

    Page<CountryCovid19Statistics> getAll(@NonNull Pageable pageable);
}
