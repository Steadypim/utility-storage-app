package dev.steadypim.thewhitehw.homework1.api.utilitystorage.mapper;

import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UpdateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.UpdateUtilityRecordArgument;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageMapper {
    UtilityRecordDTO toDto(UtilityStorage record);

    List<UtilityRecordDTO> toDtoList(List<UtilityStorage> records);

    CreateUtilityRecordArgument toCreateArgument(CreateUtilityRecordDTO dto);

    UpdateUtilityRecordArgument toUpdateArgument(UpdateUtilityRecordDTO dto);
}
