package dev.steadypim.thewhitehw.homework1.api.statistics.dtos;

public record StatisticsDTO(
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
