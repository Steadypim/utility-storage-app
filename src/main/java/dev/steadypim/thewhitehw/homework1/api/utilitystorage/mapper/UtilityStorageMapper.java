package dev.steadypim.thewhitehw.homework1.api.utilitystorage.mapper;

import dev.steadypim.thewhitehw.homework1.api.PageDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UtilityStorageSearchCriteriaDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UpdateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.SearchUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.UpdateUtilityRecordArgument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageMapper {
    UtilityRecordDTO toDto(UtilityStorage record);

    CreateUtilityRecordArgument toCreateArgument(CreateUtilityRecordDTO dto);

    UpdateUtilityRecordArgument toUpdateArgument(UpdateUtilityRecordDTO dto);

    SearchUtilityRecordArgument toSearchArgument(UtilityStorageSearchCriteriaDTO argument, Pageable pageable);


    @Mapping(source = "resultPage.content", target = "content")
    @Mapping(source = "resultPage.totalElements", target = "totalElements")
    PageDTO<UtilityRecordDTO> toSearchResultDTO(Page<UtilityStorage> resultPage);
}
