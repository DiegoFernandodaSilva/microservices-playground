package com.github.diegofernandodasilva.auditlog.service;

import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;

public interface AuditLogService {

    AuditLog storeEvent(AuditLog event);

}
