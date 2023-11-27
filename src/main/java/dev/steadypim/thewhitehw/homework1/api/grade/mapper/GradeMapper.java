package dev.steadypim.thewhitehw.homework1.api.grade.mapper;

import dev.steadypim.thewhitehw.homework1.action.create.grade.CreateGradeActionArgument;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeDTO;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GradeMapper {

    CreateGradeActionArgument toCreateActionArgument(CreateGradeDTO dto);

    GradeDTO toDto(Grade grade);

    List<GradeDTO> toDtoList(List<Grade> grades);
}
