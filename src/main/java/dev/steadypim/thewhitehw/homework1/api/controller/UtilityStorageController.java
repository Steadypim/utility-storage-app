package dev.steadypim.thewhitehw.homework1.api.controller;

import dev.steadypim.thewhitehw.homework1.api.dtos.CreateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.dtos.UpdateUtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.mapper.UtilityStorageMapper;
import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import dev.steadypim.thewhitehw.homework1.service.argument.CreateUtilityRecordArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер для работы с хранилищем полезностей")
@RestController
@RequestMapping(value = "api/v1/utilityStorage", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UtilityStorageController {

    private final UtilityStorageService service;
    private final UtilityStorageMapper mapper;


    @GetMapping("{id}")
    @Operation(description = "Получение записи по идентификатору")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityRecordDTO findById(@PathVariable("id") int id) {
        return mapper.toDto(service.displayRecordById(id));
    }

    @GetMapping("name")
    @Operation(description = "Поиск записей по имени")
    public Page<UtilityRecordDTO> findAllByName(@RequestParam("name") String name,
                                                @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {
        Page<UtilityRecord> records = service.displayRecordsByName(name, pageable);
        List<UtilityRecordDTO> dto = mapper.toDtoList(records.getContent());

        return new PageImpl<>(dto, pageable, records.getTotalElements());
    }

    @PostMapping("create")
    @Operation(description = "Добавление записи")
    public CreateUtilityRecordDTO create(@RequestBody CreateUtilityRecordDTO dto) {
        CreateUtilityRecordArgument record = mapper.toCreateArgument(dto);
        return mapper.toCreateDto(service.createRecord(record));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление записи по id")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteRecordById(id);
    }

    @PutMapping("{id}")
    @Operation(description = "Изменение записи по id")
    public void updateById(@RequestBody UpdateUtilityRecordDTO dto, @PathVariable("id") int id) {
        service.updateRecordById(mapper.toUpdateEntity(dto), id);
    }
}
