package com.github.diegofernandodasilva.covid19tracker.config;

import com.github.diegofernandodasilva.covid19tracker.rest.client.Covid19ApiRestClient;
import com.github.diegofernandodasilva.covid19tracker.rest.client.WuhanCoronavirusRestClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Configuration
public class Config {


}
