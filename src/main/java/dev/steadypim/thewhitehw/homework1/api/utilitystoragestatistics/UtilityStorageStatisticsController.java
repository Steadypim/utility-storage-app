package dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics.dtos.UtilityStorageStatisticsDTO;
import dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics.mapper.UtilityStorageStatisticsMapper;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.UtilityStorageStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stat")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы со статистикой хранилища полезностей")
public class UtilityStorageStatisticsController {

    private final UtilityStorageStatisticsService service;
    private final UtilityStorageStatisticsMapper mapper;

    @GetMapping
    @Operation(description = "Получение статистики")
    public UtilityStorageStatisticsDTO getStatistics(){
        return mapper.toDto(service.getStatistics());
    }
}
