package com.github.diegofernandodasilva.auditlog.consumer;

import com.github.diegofernandodasilva.auditlog.mapper.AuditLogMapper;
import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLogContext;
import com.github.diegofernandodasilva.auditlog.service.AuditLogService;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class AuditLogEventConsumer {

    private AuditLogService service;

    @Autowired
    public AuditLogEventConsumer(AuditLogService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${microservices.kafka.topic.audit.log}", groupId = "${spring.application.name}")
    public void listen(@Payload ConsumerRecord<String, AuditLogEvent> record) throws InterruptedException {
        log.info("Kafka listener receive message, value={}", record);
        service.storeEvent(AuditLogMapper.INSTANCE.map(record.value()));
    }
}
