package com.github.diegofernandodasilva.auditlog.service;

import com.github.diegofernandodasilva.auditlog.repository.AuditLogEventRepository;
import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditLogServiceImpl implements AuditLogService {

    private AuditLogEventRepository repository;

    @Autowired
    public AuditLogServiceImpl(AuditLogEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuditLog storeEvent(AuditLog event) {
        log.info("Creating new audit event: {}", event);
        return repository.save(event);
    }

}

