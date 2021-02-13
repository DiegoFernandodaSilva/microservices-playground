package com.github.diegofernandodasilva.covid19tracker.audit;

import com.github.diegofernandodasilva.microservices.playground.auditLog.ActionStatus;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogContext;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Covid19TrackerAuditLogEventGenerator {

    public static final String SOURCE = "Covid19TrackerService";

    public AuditLogEvent generate(String action, ActionStatus status, Instant timestamp, AuditLogContext context) {
        return AuditLogEvent.newBuilder()
                .setTimestamp(timestamp.toEpochMilli())
                .setSource(SOURCE)
                .setAction(action)
                .setStatus(status)
                .setContext(context)
                .build();
    }


}
