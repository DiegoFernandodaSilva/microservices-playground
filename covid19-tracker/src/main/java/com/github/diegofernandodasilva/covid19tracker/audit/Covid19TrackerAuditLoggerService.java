package com.github.diegofernandodasilva.covid19tracker.audit;

import com.github.diegofernandodasilva.covid19tracker.audit.context.AuditLogContextProvider;
import com.github.diegofernandodasilva.covid19tracker.audit.model.enums.AuditAction;
import com.github.diegofernandodasilva.microservices.playground.auditLog.ActionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class Covid19TrackerAuditLoggerService {

    private final AuditLogSender sender;
    private final AuditLogContextProvider contextProvider;
    private final Covid19TrackerAuditLogEventGenerator eventGenerator;

    @Autowired
    public Covid19TrackerAuditLoggerService(AuditLogSender sender,
                                            AuditLogContextProvider contextProvider,
                                            Covid19TrackerAuditLogEventGenerator eventGenerator) {
        this.sender = sender;
        this.contextProvider = contextProvider;
        this.eventGenerator = eventGenerator;
    }

    public void log(AuditAction action, ActionStatus status, Instant timestamp) {
        if (contextProvider.isSuitable()) {
            sender.send(eventGenerator.generate(action.name(), status, timestamp, contextProvider.getAuditLogContext()));
            return;
        }
        throw new IllegalStateException("No suitable auditLog context provider.");
    }
}
