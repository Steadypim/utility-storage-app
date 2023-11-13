package dev.steadypim.thewhitehw.homework1.api.utilityStorage.mapper;

import dev.steadypim.thewhitehw.homework1.api.utilityStorage.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilityStorage.dtos.UpdateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilityStorage.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.UpdateUtilityRecordArgument;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageMapper {
    UtilityRecordDTO toDto(UtilityRecord record);

    List<UtilityRecordDTO> toDtoList(List<UtilityRecord> records);

    CreateUtilityRecordArgument toCreateArgument(CreateUtilityRecordDTO dto);

    UpdateUtilityRecordArgument toUpdateArgument(UpdateUtilityRecordDTO dto);
}
