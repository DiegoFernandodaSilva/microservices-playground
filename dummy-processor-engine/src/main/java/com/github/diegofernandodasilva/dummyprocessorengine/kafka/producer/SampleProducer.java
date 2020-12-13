package com.github.diegofernandodasilva.dummyprocessorengine.kafka.producer;

import com.github.diegofernandodasilva.microservices.playground.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleProducer {

    @Autowired
    private KafkaTemplate<String, Sample> kafkaTemplate;

    @Value("${sample.topic}")
    private String sampleTopic;

    public void produceSample(Sample event) {
        log.info("Sending event: {} -> topic: {}", event, sampleTopic);
        kafkaTemplate.send(sampleTopic, event);
    }
}
