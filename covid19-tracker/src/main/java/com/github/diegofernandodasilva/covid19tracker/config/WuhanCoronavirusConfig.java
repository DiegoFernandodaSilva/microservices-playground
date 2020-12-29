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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties
public class WuhanCoronavirusConfig {

    public static final String COVID19_REST_CLIENT = "covid19RestClient";
    public static final String COVID19_REST_REST_TEMPLATE = "covid19RestTemplate";

    @Bean(name = COVID19_REST_CLIENT)
    @ConditionalOnMissingBean(name = COVID19_REST_CLIENT)
    public Covid19ApiRestClient covid19RestClient(WuhanCoronavirusProps wuhanCoronavirusProps, RestTemplate restTemplate) {
        return new WuhanCoronavirusRestClient(wuhanCoronavirusProps, restTemplate);
    }

    @Bean
    @ConfigurationProperties(prefix = "wuhan-coronavirus")
    public WuhanCoronavirusProps wuhanCoronavirusProps(){
        return new WuhanCoronavirusProps();
    }

    @Bean(name= COVID19_REST_REST_TEMPLATE)
    @ConditionalOnMissingBean(name = COVID19_REST_REST_TEMPLATE)
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

}
