package dev.steadypim.thewhitehw.homework1.api.grade.mapper;

import dev.steadypim.thewhitehw.homework1.action.create.grade.CreateGradeActionArgument;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeSearchCriteriaDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.SearchGradeResultDTO;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.SearchGradeArgument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GradeMapper {

    CreateGradeActionArgument toCreateActionArgument(CreateGradeDTO dto);

    GradeDTO toDto(Grade grade);

    SearchGradeArgument toSearchArgument(GradeSearchCriteriaDTO argument, Pageable pageable);
    @Mapping(source = "resultPage.content", target = "grades")
    @Mapping(source = "resultPage.totalElements", target = "totalElements")
    SearchGradeResultDTO toSearchResultDTO(Page<Grade> resultPage);

}
