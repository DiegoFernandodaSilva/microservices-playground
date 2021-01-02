package com.github.diegofernandodasilva.auditlog.mapper;

import com.github.diegofernandodasilva.auditlog.repository.entity.AuditLog;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    AuditLogMapper INSTANCE = Mappers.getMapper(AuditLogMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    }
    )
    AuditLog map(AuditLogEvent source);

    default Instant map(Long timestamp) {
        return Instant.ofEpochMilli(timestamp);
    }

}
