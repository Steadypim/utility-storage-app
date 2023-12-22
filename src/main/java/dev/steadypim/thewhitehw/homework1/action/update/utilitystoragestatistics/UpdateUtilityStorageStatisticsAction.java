package dev.steadypim.thewhitehw.homework1.action.update.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.event.utilitystoragestatistics.StatisticsEvent;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeService;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.UtilityStorageStatisticsService;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateUtilityStorageStatisticsArgument;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UpdateUtilityStorageStatisticsAction {
    UtilityStorageStatisticsService utilityStorageStatisticsService;
    UtilityStorageService utilityStorageService;
    GradeService gradeService;

    @EventListener
    @Async("statisticsExecutor")
    public void onStatisticsEvent(StatisticsEvent event) {

        UpdateUtilityStorageStatisticsArgument argument = UpdateUtilityStorageStatisticsArgument.builder()
                .totalRecords(utilityStorageService.getTotalRecords())
                .totalGrades(gradeService.getTotalGrades())
                .averageGradeOfEntireStorage(utilityStorageService.calculateAverageGradeOfEntireStorage())
                .numberOfRecordsWithMaxAverageGrade(utilityStorageService.calculateNumberOfRecordsWithMaxAverageGrade())
                .percentageOfRecordsWithMaxAverageGrade(utilityStorageService.calculatePercentageOfRecordsWithMaxAverageGrade())
                .numberOfRecordsWithoutGradesBelowFour(utilityStorageService.calculateRecordsWithoutGradesBelowFour())
                .percentageOfRecordsWithoutGradesBelowFour(utilityStorageService.calculatePercentageRecordsWithoutGradesBelowFour())
                .numberOfRecordsWithAverageGradeFourOrHigher(utilityStorageService.calculateNumberOfRecordsWithAverageGradeFourOrHigher())
                .percentageOfRecordsWithAverageGradeFourOrHigher(utilityStorageService.calculatePercentageOfRecordsWithAverageGradeFourOrHigher())
                .numberOfRecordsWithoutGrades(utilityStorageService.calculateNumberOfRecordsWithoutGrades())
                .build();

        utilityStorageStatisticsService.updateStatistics(argument);
    }

}
