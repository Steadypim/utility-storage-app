package dev.steadypim.thewhitehw.homework1.api.statistics.mapper;

import dev.steadypim.thewhitehw.homework1.api.statistics.dtos.StatisticsDTO;
import dev.steadypim.thewhitehw.homework1.entity.Statistics;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface StatisticsMapper {
    StatisticsDTO toDto(Statistics statistics);
}
