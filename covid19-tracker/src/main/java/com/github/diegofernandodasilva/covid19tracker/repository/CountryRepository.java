package com.github.diegofernandodasilva.covid19tracker.repository;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryRegion(String countryRegion);

}
