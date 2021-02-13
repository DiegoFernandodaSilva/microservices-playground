package com.github.diegofernandodasilva.covid19tracker.audit.context;

import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogContext;

public interface AuditLogContextProvider {

    AuditLogContext getAuditLogContext();

    boolean isSuitable();
}
