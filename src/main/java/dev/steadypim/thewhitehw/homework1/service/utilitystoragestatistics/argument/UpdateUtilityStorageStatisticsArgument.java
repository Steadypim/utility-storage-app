package dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument;

import lombok.Builder;

@Builder
public record UpdateUtilityStorageStatisticsArgument(
        Long totalRecords,
        Long totalGrades,
        Double averageGradeOfEntireStorage,
        Long numberOfRecordsWithMaxAverageGrade,
        Double percentageOfRecordsWithMaxAverageGrade,
        Long numberOfRecordsWithAverageGradeFourOrHigher,
        Double percentageOfRecordsWithAverageGradeFourOrHigher,
        Long numberOfRecordsWithoutGradesBelowFour,
        Double percentageOfRecordsWithoutGradesBelowFour,
        Long numberOfRecordsWithoutGrades
) {

}
