package dev.steadypim.thewhitehw.homework1.service.statistics;

import dev.steadypim.thewhitehw.homework1.entity.Statistics;
import dev.steadypim.thewhitehw.homework1.repository.utilitystoragestatistics.StatisticsRepository;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.StatisticsServiceImpl;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateStatisticsArgument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceImplTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    StatisticsServiceImpl statisticsService;

    @Test
    @DisplayName("testUpdateStatisticsWhenStatisticsIsNullThenCreateNewStatistics")
    void testUpdateStatisticsWhenStatisticsIsNullThenCreateNewStatistics() {
        // Arrange
        UpdateStatisticsArgument argument = UpdateStatisticsArgument.builder().build();
        when(statisticsRepository.findFirstByOrderByIdAsc()).thenReturn(null);

        // Act
        statisticsService.updateStatistics(argument);

        // Assert
        verify(statisticsRepository).findFirstByOrderByIdAsc();
        verify(statisticsRepository).save(any(Statistics.class));
    }

    @Test
    @DisplayName("testUpdateStatisticsWhenStatisticsExistsThenUpdateStatistics")
    void testUpdateStatisticsWhenStatisticsExistsThenUpdateStatistics() {
        // Arrange
        UpdateStatisticsArgument argument = UpdateStatisticsArgument.builder()
                                                                    .totalRecords(20L)
                                                                    .totalGrades(20L)
                                                                    .numberOfRecordsWithAverageGradeFourOrHigher(8L)
                                                                    .percentageOfRecordsWithAverageGradeFourOrHigher(1.0)
                                                                    .numberOfRecordsWithoutGradesBelowFour(3L)
                                                                    .percentageOfRecordsWithoutGradesBelowFour(23.0)
                                                                    .numberOfRecordsWithMaxAverageGrade(4L)
                                                                    .percentageOfRecordsWithMaxAverageGrade(51.0)
                                                                    .numberOfRecordsWithoutGrades(4L)
                                                                    .averageGradeOfEntireStorage(3.0)
                                                                    .build();
        Statistics existingStatistics = new Statistics();
        when(statisticsRepository.findFirstByOrderByIdAsc()).thenReturn(existingStatistics);

        // Act
        statisticsService.updateStatistics(argument);

        // Assert
        verify(statisticsRepository).findFirstByOrderByIdAsc();
        verify(statisticsRepository).save(existingStatistics);
        assertEquals(20L, existingStatistics.getTotalRecords());
        assertEquals(20L, existingStatistics.getTotalGrades());
        assertEquals(3.0, existingStatistics.getAverageGradeOfEntireStorage());
        assertEquals(4L, existingStatistics.getNumberOfRecordsWithMaxAverageGrade());
        assertEquals(51.0, existingStatistics.getPercentageOfRecordsWithMaxAverageGrade());
        assertEquals(8L, existingStatistics.getNumberOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(1.0, existingStatistics.getPercentageOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(3L, existingStatistics.getNumberOfRecordsWithoutGradesBelowFour());
        assertEquals(23.0, existingStatistics.getPercentageOfRecordsWithoutGradesBelowFour());
        assertEquals(4L, existingStatistics.getNumberOfRecordsWithoutGrades());
    }

    @Test
    @DisplayName("testGetStatisticsWhenStatisticsIsNullThenReturnNewStatistics")
    void testGetStatisticsWhenStatisticsIsNullThenReturnNewStatistics() {
        // Arrange
        when(statisticsRepository.findFirstByOrderByIdAsc()).thenReturn(null);

        // Act
        Statistics result = statisticsService.getStatistics();

        // Assert
        verify(statisticsRepository).findFirstByOrderByIdAsc();
        assertEquals(new Statistics(), result);
    }

    @Test
    @DisplayName("testGetStatisticsWhenStatisticsExistsThenReturnExistingStatistics")
    void testGetStatisticsWhenStatisticsExistsThenReturnExistingStatistics() {
        // Arrange
        Statistics existingStatistics = new Statistics();
        when(statisticsRepository.findFirstByOrderByIdAsc()).thenReturn(existingStatistics);

        // Act
        Statistics result = statisticsService.getStatistics();

        // Assert
        verify(statisticsRepository).findFirstByOrderByIdAsc();
        assertEquals(existingStatistics, result);
    }
}
