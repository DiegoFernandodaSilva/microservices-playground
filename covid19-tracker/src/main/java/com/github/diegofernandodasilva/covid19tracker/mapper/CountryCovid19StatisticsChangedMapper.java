package com.github.diegofernandodasilva.covid19tracker.mapper;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.microservices.playground.covid19tracker.CountryCovid19StatisticsChanged;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface CountryCovid19StatisticsChangedMapper {

    CountryCovid19StatisticsChangedMapper INSTANCE = Mappers.getMapper(CountryCovid19StatisticsChangedMapper.class);

    @Mappings({
            @Mapping(target = "countryRegion", source = "country.countryRegion"),
            @Mapping(target = "confirmed", source = "statistics.confirmed"),
            @Mapping(target = "deaths", source = "statistics.deaths"),
            @Mapping(target = "recovered", source = "statistics.recovered"),
    })
    CountryCovid19StatisticsChanged map(CountryCovid19Statistics source);

    default Long map(Instant instant) {
        return instant.toEpochMilli();
    }

}
