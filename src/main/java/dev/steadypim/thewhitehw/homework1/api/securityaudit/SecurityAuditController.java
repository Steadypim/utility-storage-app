package dev.steadypim.thewhitehw.homework1.api.securityaudit;

import dev.steadypim.thewhitehw.homework1.api.PageDTO;
import dev.steadypim.thewhitehw.homework1.api.securityaudit.dtos.SecurityAuditDTO;
import dev.steadypim.thewhitehw.homework1.api.securityaudit.dtos.SecurityAuditSearchCriteriaDTO;
import dev.steadypim.thewhitehw.homework1.api.securityaudit.mapper.SecurityAuditMapper;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.SecurityAuditService;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.SearchSecurityAuditArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Tag(name = "Контроллер для работы с аудитом")
@RestController
@RequestMapping("audit")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SecurityAuditController {

    SecurityAuditService service;
    SecurityAuditMapper mapper;

    @GetMapping("search")
    @Operation(description = "Получение постраничного списка аудита с поиском по полю info")
    public PageDTO<SecurityAuditDTO> searchSecurityAudit(
            @ModelAttribute SecurityAuditSearchCriteriaDTO criteriaDTO,
            @PageableDefault(sort = "info", direction = ASC) Pageable pageable) {

        SearchSecurityAuditArgument securityAuditArgument = mapper.toSearchArgument(criteriaDTO, pageable);

        return mapper.toSearchResultDTO(service.searchSecurityAudit(securityAuditArgument));
    }

}
