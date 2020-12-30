package com.github.diegofernandodasilva.covid19tracker.service;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import lombok.NonNull;

import java.util.Optional;

public interface CountryService {

    Country create(@NonNull Country country);

    Optional<Country> findByCountryRegion(@NonNull String countryRegion);
}
