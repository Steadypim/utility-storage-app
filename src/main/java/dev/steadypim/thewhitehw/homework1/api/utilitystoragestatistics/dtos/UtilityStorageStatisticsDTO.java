package dev.steadypim.thewhitehw.homework1.api.utilitystoragestatistics.dtos;

public record UtilityStorageStatisticsDTO(
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
