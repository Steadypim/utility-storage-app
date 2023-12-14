package dev.steadypim.thewhitehw.homework1.service.securityaudit;

import dev.steadypim.thewhitehw.homework1.entity.SecurityAudit;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.CreateSecurityAuditArgument;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.SearchSecurityAuditArgument;
import org.springframework.data.domain.Page;

public interface SecurityAuditService {
    Page<SecurityAudit> searchSecurityAudit(SearchSecurityAuditArgument argument);

    SecurityAudit create(CreateSecurityAuditArgument argument);
}
