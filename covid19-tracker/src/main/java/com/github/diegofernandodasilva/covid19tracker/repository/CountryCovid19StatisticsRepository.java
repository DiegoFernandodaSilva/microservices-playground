package com.github.diegofernandodasilva.covid19tracker.repository;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryCovid19StatisticsRepository extends JpaRepository<CountryCovid19Statistics, Long> {

    Optional<CountryCovid19Statistics> findByCountry(Country country);

}
