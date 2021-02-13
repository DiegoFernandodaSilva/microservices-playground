package com.github.diegofernandodasilva.covid19tracker.audit;

import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;

public interface AuditLogSender {

    void send(AuditLogEvent event);

}
