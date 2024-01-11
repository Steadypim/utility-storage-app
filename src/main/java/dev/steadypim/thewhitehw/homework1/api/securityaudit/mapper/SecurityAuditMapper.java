package dev.steadypim.thewhitehw.homework1.api.securityaudit.mapper;

import dev.steadypim.thewhitehw.homework1.api.PageDTO;
import dev.steadypim.thewhitehw.homework1.api.securityaudit.dtos.SecurityAuditDTO;
import dev.steadypim.thewhitehw.homework1.api.securityaudit.dtos.SecurityAuditSearchCriteriaDTO;
import dev.steadypim.thewhitehw.homework1.entity.SecurityAudit;
import dev.steadypim.thewhitehw.homework1.service.securityaudit.argument.SearchSecurityAuditArgument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SecurityAuditMapper {
    SearchSecurityAuditArgument toSearchArgument(SecurityAuditSearchCriteriaDTO criteriaDTO, Pageable pageable);

    @Mapping(source = "searchSecurityAudit.content", target = "content")
    @Mapping(source = "searchSecurityAudit.totalElements", target = "totalElements")
    PageDTO<SecurityAuditDTO> toSearchResultDTO(Page<SecurityAudit> searchSecurityAudit);
}
