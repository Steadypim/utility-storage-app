package dev.steadypim.thewhitehw.homework1.repository.securityaudit;

import dev.steadypim.thewhitehw.homework1.entity.SecurityAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий для работы с данными в SecurityAudit
 */
@Repository
public interface SecurityAuditRepository extends JpaRepository<SecurityAudit, Integer>, QuerydslPredicateExecutor<SecurityAudit> {
}
