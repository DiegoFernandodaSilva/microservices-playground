package com.github.diegofernandodasilva.covid19tracker.mapper;

import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.Location;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryCovid19StatisticsDTO;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.LocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryCovid19StatisticsMapper {

    CountryCovid19StatisticsMapper INSTANCE = Mappers.getMapper(CountryCovid19StatisticsMapper.class);

    @Mappings({
            @Mapping(target = "confirmed", source = "source.statistics.confirmed"),
            @Mapping(target = "deaths", source = "source.statistics.deaths"),
            @Mapping(target = "recovered", source = "source.statistics.recovered"),
            @Mapping(target = "countryRegion", source = "source.country.countryRegion"),
            @Mapping(target = "location", source = "source.country.location"),
            @Mapping(target = "countryEntryCode", source = "source.country.countryEntryCode"),
    })
    CountryCovid19StatisticsDTO map(CountryCovid19Statistics source);

    @Mappings({
            @Mapping(target = "statistics.confirmed", source = "source.confirmed"),
            @Mapping(target = "statistics.deaths", source = "source.deaths"),
            @Mapping(target = "statistics.recovered", source = "source.recovered"),
            @Mapping(target = "country.countryEntryCode", source = "source.countryEntryCode"),
            @Mapping(target = "country.location", source = "source.location"),
            @Mapping(target = "country.countryRegion", source = "source.countryRegion"),
            @Mapping(ignore = true, target = "country.id"),
            @Mapping(ignore = true, target = "id"),
    })
    CountryCovid19Statistics map(CountryCovid19StatisticsDTO source);

    default Location map(LocationDTO source) {
        return new Location(source.getLat(), source.getLng());
    }

    default LocationDTO map(Location source) {
        return new LocationDTO(source.getLat(), source.getLon());
    }

}
