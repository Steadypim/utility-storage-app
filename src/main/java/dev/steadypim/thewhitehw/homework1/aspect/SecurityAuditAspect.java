package dev.steadypim.thewhitehw.homework1.aspect;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.SecurityAuditService;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.CreateSecurityAuditArgument;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static dev.steadypim.thewhitehw.homework1.general.network.NetworkInfoProvider.obtainIpAddress;
import static dev.steadypim.thewhitehw.homework1.general.network.NetworkInfoProvider.obtainUserAgent;
import static java.time.OffsetDateTime.now;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAuditAspect {

    private final SecurityAuditService service;

    @Pointcut("execution(* dev.steadypim.thewhitehw.homework1.service.grade.GradeServiceImpl.create(..))")
    private void createGradePointcut() {

    }

    @AfterReturning(pointcut = "createGradePointcut()", returning = "grade")
    public void afterCreatingGrade(Object grade) {
        if (grade instanceof Grade gradeInstance) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String info;
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String ipAddress = obtainIpAddress(request);
                String userAgent = obtainUserAgent(attributes);
                info = "IP: " + ipAddress + "; User-Agent: " + userAgent;

            }
            else {
                info = "Не удалось получить";
            }
            CreateSecurityAuditArgument auditArgument = CreateSecurityAuditArgument.builder()
                    .gradeId(gradeInstance.getId())
                    .info(info)
                    .createdAt(now())
                    .build();

            service.create(auditArgument);
        }
    }
}
