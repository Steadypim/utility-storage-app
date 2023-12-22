package dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics.mapper;

import dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics.dtos.UtilityStorageStatisticsDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorageStatistics;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageStatisticsMapper {
    UtilityStorageStatisticsDTO toDto(UtilityStorageStatistics statistics);
}
