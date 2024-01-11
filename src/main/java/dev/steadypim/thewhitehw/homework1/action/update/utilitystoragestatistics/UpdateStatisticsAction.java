package dev.steadypim.thewhitehw.homework1.action.update.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.event.statistics.StatisticsEvent;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeService;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.StatisticsService;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateStatisticsArgument;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UpdateStatisticsAction {

    StatisticsService statisticsService;
    UtilityStorageService utilityStorageService;
    GradeService gradeService;


    @EventListener
    @Async("statisticsExecutor")
    public void onStatisticsEvent(StatisticsEvent event) {
        var argument = UpdateStatisticsArgument.builder()
                                               .totalRecords(utilityStorageService.getTotalRecords())
                                               .totalGrades(gradeService.getTotalGrades())
                                               .averageGradeOfEntireStorage(gradeService.countAverageGradeOfEntireStorage())
                                               .numberOfRecordsWithMaxAverageGrade(utilityStorageService.countRecordsWithAverageGradeEqualsFive())
                                               .percentageOfRecordsWithMaxAverageGrade(utilityStorageService.percentageOfRecordsWithAverageGradeEqualsFive())
                                               .numberOfRecordsWithoutGradesBelowFour(utilityStorageService.countRecordsWithoutGradesBelowFour())
                                               .percentageOfRecordsWithoutGradesBelowFour(utilityStorageService.percentageRecordsWithoutGradesBelowFour())
                                               .numberOfRecordsWithAverageGradeFourOrHigher(utilityStorageService.countRecordsWithAverageGradeEqualsFourOrHigher())
                                               .percentageOfRecordsWithAverageGradeFourOrHigher(utilityStorageService.percentageOfRecordsWithAverageGradeFourOrHigher())
                                               .numberOfRecordsWithoutGrades(utilityStorageService.countRecordsWithoutGrades())
                                               .build();

        statisticsService.updateStatistics(argument);

    }

}
