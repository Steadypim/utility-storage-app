package dev.steadypim.thewhitehw.homework1.service.securityaudit.argument;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class CreateSecurityAuditArgument {
    Integer gradeId;
    String info;
    OffsetDateTime createdAt;
}
