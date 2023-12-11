package dev.steadypim.thewhitehw.homework1.api.securityaudit.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SecurityAuditDTO {
    Integer gradeId;
    String info;
    OffsetDateTime createdAt;
}
