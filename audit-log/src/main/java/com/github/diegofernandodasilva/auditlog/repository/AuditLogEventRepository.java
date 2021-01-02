package com.github.diegofernandodasilva.auditlog.repository;

import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogEventRepository extends JpaRepository<AuditLog, Long> {
}
