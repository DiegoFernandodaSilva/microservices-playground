package com.github.diegofernandodasilva.covid19tracker.service.impl;

import com.github.diegofernandodasilva.covid19tracker.service.CountryCovid19StatisticsChangedSenderService;
import com.github.diegofernandodasilva.microservices.playground.covid19tracker.CountryCovid19StatisticsChanged;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryCovid19StatisticsChangedSenderServiceImpl implements CountryCovid19StatisticsChangedSenderService {

    private String topic;
    private KafkaTemplate<String, CountryCovid19StatisticsChanged> kafkaTemplate;

    @Autowired
    public CountryCovid19StatisticsChangedSenderServiceImpl(
            @Value("${microservices.kafka.topic.country.covid19.statistics.changed}") String topic,
            KafkaTemplate<String, CountryCovid19StatisticsChanged> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(CountryCovid19StatisticsChanged event) {
        log.info("Sending event: {} to topic: {}", event, topic);
        kafkaTemplate.send(topic, event);
    }
}
