package com.github.diegofernandodasilva.auditlog.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogContext {

    @Column(name = "user_name")
    private String user;
    private String ipAddress;

}
