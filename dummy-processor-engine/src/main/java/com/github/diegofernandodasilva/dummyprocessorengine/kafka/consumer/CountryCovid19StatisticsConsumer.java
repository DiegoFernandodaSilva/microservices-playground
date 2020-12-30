package com.github.diegofernandodasilva.dummyprocessorengine.kafka.consumer;

import com.github.diegofernandodasilva.microservices.playground.Sample;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CountryCovid19StatisticsConsumer {

    @KafkaListener(topics = "${microservices.kafka.topic.country.covid19.statistics.changed}", groupId = "${spring.application.name}")
    public void listen(@Payload ConsumerRecord<Void, Sample> record) throws InterruptedException {
      log.info("Kafka listener receive message, value={}", record.value());
      Thread.sleep(10_000);
    }
}
