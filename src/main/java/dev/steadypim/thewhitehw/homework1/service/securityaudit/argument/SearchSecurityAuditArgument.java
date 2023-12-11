package dev.steadypim.thewhitehw.homework1.service.securityaudit.argument;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@FieldDefaults(level = PRIVATE)
public class SearchSecurityAuditArgument {
    String info;
    Pageable pageable;
}
