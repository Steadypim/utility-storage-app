package dev.steadypim.thewhitehw.homework1.service.securityaudit;

import com.querydsl.core.BooleanBuilder;
import dev.steadypim.thewhitehw.homework1.entity.QSecurityAudit;
import dev.steadypim.thewhitehw.homework1.entity.SecurityAudit;
import dev.steadypim.thewhitehw.homework1.repository.securityaudit.SecurityAuditRepository;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.CreateSecurityAuditArgument;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.SearchSecurityAuditArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecurityAuditServiceImpl implements SecurityAuditService {

    private final SecurityAuditRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<SecurityAudit> searchSecurityAudit(SearchSecurityAuditArgument argument) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (argument.getInfo() != null) {
            predicate.and(QSecurityAudit.securityAudit.info.eq(argument.getInfo()));
        }
        return repository.findAll(predicate, argument.getPageable());
    }

    @Override
    @Transactional
    public SecurityAudit create(CreateSecurityAuditArgument argument) {
        SecurityAudit audit = SecurityAudit.builder()
                                           .gradeId(argument.getGradeId())
                                           .info(argument.getInfo())
                                           .createdAt(argument.getCreatedAt())
                                           .build();

        return repository.save(audit);
    }
}
