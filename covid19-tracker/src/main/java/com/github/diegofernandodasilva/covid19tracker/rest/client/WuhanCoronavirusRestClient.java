package com.github.diegofernandodasilva.covid19tracker.rest.client;

import com.github.diegofernandodasilva.covid19tracker.config.WuhanCoronavirusProps;
import com.github.diegofernandodasilva.covid19tracker.mapper.CountryCovid19StatisticsMapper;
import com.github.diegofernandodasilva.covid19tracker.repository.entity.CountryCovid19Statistics;
import com.github.diegofernandodasilva.covid19tracker.rest.dto.CountryCovid19StatisticsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.diegofernandodasilva.covid19tracker.config.WuhanCoronavirusProps.*;

@Slf4j
public class WuhanCoronavirusRestClient implements Covid19ApiRestClient {

    private WuhanCoronavirusProps wuhanCoronavirusConfigProps;
    private RestTemplate restTemplate;

    public WuhanCoronavirusRestClient(WuhanCoronavirusProps wuhanCoronavirusConfigProps, RestTemplate restTemplate) {
        this.wuhanCoronavirusConfigProps = wuhanCoronavirusConfigProps;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CountryCovid19Statistics> getCountriesCovid19Statistics() {
        return performGetCountriesCovid19StatisticsDTORequest()
                .stream()
                .map(CountryCovid19StatisticsMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    private List<CountryCovid19StatisticsDTO> performGetCountriesCovid19StatisticsDTORequest() {
        String uri = UriComponentsBuilder
                .fromUriString(wuhanCoronavirusConfigProps.getUrl() + LATEST)
                .queryParam(ONLY_COUNTRIES_NAME_PARAM, true).toUriString();

        log.info("Http request: GET {}", uri);
        return restTemplate.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CountryCovid19StatisticsDTO>>() {
                }).getBody();
    }
}
