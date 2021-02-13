package com.github.diegofernandodasilva.auditlog.service;

import com.github.diegofernandodasilva.auditlog.repository.AuditLogEventRepository;
import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLogContext;
import com.github.diegofernandodasilva.microservices.playground.auditLog.ActionStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

@SpringBootTest
public class AuditLogServiceTest {

    @Autowired
    private AuditLogService auditLogService;

    @MockBean
    private AuditLogEventRepository repository;

    @Test
    public void storeEventTest() {
        final AuditLog event = event();
        auditLogService.storeEvent(event);
        Mockito.verify(repository, Mockito.times(1)).save(event);
    }

    private AuditLog event() {
        return new AuditLog(
                null, "source", "action",
                new AuditLogContext("user1", "127.0.0.1"),
                ActionStatus.SUCCESS.name(),
                Instant.now()
        );
    }
}