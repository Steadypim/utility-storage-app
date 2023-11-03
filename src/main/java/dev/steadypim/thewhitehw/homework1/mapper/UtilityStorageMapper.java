package dev.steadypim.thewhitehw.homework1.mapper;

import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageMapper {
    UtilityRecord toEntity(UtilityRecordDTO dto);

    UtilityRecordDTO toDto(UtilityRecord record);

    List<UtilityRecordDTO> toDtoList(List<UtilityRecord> records);
}
