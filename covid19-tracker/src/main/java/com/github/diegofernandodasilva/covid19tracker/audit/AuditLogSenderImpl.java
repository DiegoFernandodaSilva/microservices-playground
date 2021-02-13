package com.github.diegofernandodasilva.covid19tracker.audit;

import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditLogSenderImpl implements AuditLogSender {

    private KafkaTemplate<String, AuditLogEvent> kafkaTemplate;
    private String kafkaTopic;

    @Autowired
    public AuditLogSenderImpl(KafkaTemplate<String, AuditLogEvent> kafkaTemplate, @Value("${microservices.kafka.topic.audit.log}") String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    public void send(AuditLogEvent event) {
        log.info("Sending event: {} to topic: {}", event, kafkaTopic);
        kafkaTemplate.send(kafkaTopic, event);
    }
}
