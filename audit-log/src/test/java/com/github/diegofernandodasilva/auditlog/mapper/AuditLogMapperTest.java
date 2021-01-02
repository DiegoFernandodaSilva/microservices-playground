package com.github.diegofernandodasilva.auditlog.mapper;

import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import com.github.diegofernandodasilva.microservices.playground.auditLog.ActionStatus;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogContext;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class AuditLogMapperTest {

    @Test
    public void mapEventToAuditLog() {
        AuditLogEvent event = AuditLogEvent.newBuilder()
                .setAction("Action")
                .setSource("Source")
                .setStatus(ActionStatus.SUCCESS)
                .setTimestamp(Instant.now().toEpochMilli())
                .setContext(
                        AuditLogContext.newBuilder().setUser("Johny").setIpAddress("127.0.0.0").build()
                ).build();

        AuditLog auditLog = AuditLogMapper.INSTANCE.map(event);

        assertEquals(event.getAction(), auditLog.getAction());
        assertEquals(event.getSource(), auditLog.getSource());
        assertEquals(event.getStatus().name(), auditLog.getStatus());
        assertEquals(event.getTimestamp(), auditLog.getTimestamp().toEpochMilli());
        assertEquals(event.getContext().getIpAddress(), auditLog.getContext().getIpAddress());
        assertEquals(event.getContext().getUser(), auditLog.getContext().getUser());

    }
}
