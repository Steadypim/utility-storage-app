package dev.steadypim.thewhitehw.homework1.api.grade.mapper;

import dev.steadypim.thewhitehw.homework1.action.create.grade.CreateGradeActionArgument;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeDTO;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GradeMapper {

    CreateGradeActionArgument toCreateActionArgument(CreateGradeDTO dto);

    @Mapping(source = "utilityStorage.id", target = "utilityStorageId")
    GradeDTO toDto(Grade grade);
}
