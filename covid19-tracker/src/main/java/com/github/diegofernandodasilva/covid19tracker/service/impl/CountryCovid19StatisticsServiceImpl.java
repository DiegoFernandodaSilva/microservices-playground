package com.github.diegofernandodasilva.covid19tracker.service.impl;

import com.github.diegofernandodasilva.covid19tracker.audit.Covid19TrackerAuditLoggerService;
import com.github.diegofernandodasilva.covid19tracker.audit.model.enums.AuditAction;
import com.github.diegofernandodasilva.covid19tracker.exception.EntityAlreadyExistsException;
import com.github.diegofernandodasilva.covid19tracker.exception.NotFoundException;
import com.github.diegofernandodasilva.covid19tracker.mapper.CountryCovid19StatisticsChangedMapper;
import com.github.diegofernandodasilva.covid19tracker.repository.CountryCovid19StatisticsRepository;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.Country;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsChangedSenderService;
import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsService;
import com.github.diegofernandodasilva.microservices.playground.auditLog.ActionStatus;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class CountryCovid19StatisticsServiceImpl implements CountryCovid19StatisticsService {

    private CountryCovid19StatisticsRepository repository;
    private CountryCovid19StatisticsChangedSenderService changedSenderService;
    private Covid19TrackerAuditLoggerService auditLoggerService;

    @Autowired
    public CountryCovid19StatisticsServiceImpl(CountryCovid19StatisticsRepository repository,
                                               CountryCovid19StatisticsChangedSenderService changedSenderService,
                                               Covid19TrackerAuditLoggerService auditLoggerService) {
        this.repository = repository;
        this.changedSenderService = changedSenderService;
        this.auditLoggerService = auditLoggerService;
    }

    @Override
    public CountryCovid19Statistics create(CountryCovid19Statistics covid19Statistics) {
        if (findByCountry(covid19Statistics.getCountry()).isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format("Covid19 statistic for country: %s already exists!", covid19Statistics.getCountry())
            );
        }
        repository.save(covid19Statistics);
        changedSenderService.send(CountryCovid19StatisticsChangedMapper.INSTANCE.map(covid19Statistics));
        return covid19Statistics;
    }

    @Override
    public void update(CountryCovid19Statistics entity) {
        CountryCovid19Statistics foundStatistics = getByCountry(entity.getCountry());
        entity.setId(foundStatistics.getId());
        repository.save(entity);
        changedSenderService.send(CountryCovid19StatisticsChangedMapper.INSTANCE.map(entity));
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
        Page<CountryCovid19Statistics> data = repository.findAll(pageable);
        auditLoggerService.log(AuditAction.DATA_VISUALIZATION, ActionStatus.SUCCESS, Instant.now());
        return data;
    }
}
