package com.github.diegofernandodasilva.covid19tracker.service.impl;

import com.github.diegofernandodasilva.covid19tracker.exception.EntityAlreadyExistsException;
import com.github.diegofernandodasilva.covid19tracker.exception.NotFoundException;
import com.github.diegofernandodasilva.covid19tracker.repository.CountryCovid19StatisticsRepository;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CountryCovid19StatisticsServiceImpl implements CountryCovid19StatisticsService {

    private CountryCovid19StatisticsRepository repository;

    @Autowired
    public CountryCovid19StatisticsServiceImpl(CountryCovid19StatisticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public CountryCovid19Statistics create(CountryCovid19Statistics covid19Statistics) {
        if (findByCountry(covid19Statistics.getCountry()).isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format("Covid19 statistic for country: %s already exists!", covid19Statistics.getCountry())
            );
        }
        return repository.save(covid19Statistics);
    }

    @Override
    public void update(CountryCovid19Statistics entity) {
        CountryCovid19Statistics foundStatistics = getByCountry(entity.getCountry());
        entity.setId(foundStatistics.getId());
        repository.save(entity);
    }

    @Override
    public CountryCovid19Statistics getByCountry(@NonNull Country country) {
        return findByCountry(country).orElseThrow(() ->
                new NotFoundException(
                        String.format("Covid19 Statistics not found for country: %s", country)
                )
        );
    }

    @Override
    public Optional<CountryCovid19Statistics> findByCountry(Country country) {
        return repository.findByCountry(country);
    }

    @Override
    public Page<CountryCovid19Statistics> getAll(@NonNull Pageable pageable) {
        return repository.findAll(pageable);
    }
}
