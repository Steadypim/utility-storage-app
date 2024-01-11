package dev.steadypim.thewhitehw.homework1.api.statistics;

import dev.steadypim.thewhitehw.homework1.api.statistics.dtos.StatisticsDTO;
import dev.steadypim.thewhitehw.homework1.api.statistics.mapper.StatisticsMapper;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы со статистикой хранилища полезностей")
public class StatisticsController {

    private final StatisticsService service;
    private final StatisticsMapper mapper;

    @GetMapping
    @Operation(description = "Получение статистики")
    public StatisticsDTO getStatistics() {
        return mapper.toDto(service.getStatistics());
    }
}
