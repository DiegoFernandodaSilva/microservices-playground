package com.github.diegofernandodasilva.covid19tracker.service.impl;

import com.github.diegofernandodasilva.covid19tracker.exception.NotFoundException;
import com.github.diegofernandodasilva.covid19tracker.repository.CountryRepository;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CountryServiceImpl implements CountryService {

    private CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country create(Country country) {
        log.debug("Creating new country {}.", country);
        return repository.save(country);
    }

    @Override
    public Optional<Country> findByCountryRegion(String countryRegion) {
        return repository.findByCountryRegion(countryRegion);
    }
}
