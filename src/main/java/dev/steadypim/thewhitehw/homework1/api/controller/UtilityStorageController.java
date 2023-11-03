package dev.steadypim.thewhitehw.homework1.api.controller;

import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.service.UtilityStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для работы с хранилищем полезностей")
@RestController
@RequestMapping(value = "api/v1/utilityStorage", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UtilityStorageController {

    private final UtilityStorageService service;

    @GetMapping("{id}")
    @Operation(description = "Получение записи по идентификатору")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityRecordDTO findById(@PathVariable("id") int id) {
        return service.displayRecordById(id);
    }

    @GetMapping("name")
    @Operation(description = "Поиск записей по имени")
    public ResponseEntity<Page<UtilityRecordDTO>> findAllByName(@RequestParam("name") String name,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<UtilityRecordDTO> pageResult = service.displayRecordsByName(name, page, size);
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping("create")
    @Operation(description = "Добавление записи")
    public UtilityRecordDTO create(@RequestBody UtilityRecordDTO dto) {
        return service.createRecord(dto);
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление записи по id")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteRecordById(id);
    }

    @PutMapping("{id}")
    @Operation(description = "Изменение записи по id")
    public void updateById(@RequestBody UtilityRecordDTO dto, @PathVariable("id") int id) {
        service.updateRecordById(dto, id);
    }
}
