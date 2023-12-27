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
        UpdateStatisticsArgument argument = UpdateStatisticsArgument.builder().build();
        Statistics existingStatistics = new Statistics();
        when(statisticsRepository.findFirstByOrderByIdAsc()).thenReturn(existingStatistics);

        // Act
        statisticsService.updateStatistics(argument);

        // Assert
        verify(statisticsRepository).findFirstByOrderByIdAsc();
        verify(statisticsRepository).save(existingStatistics);
        assertEquals(argument.totalRecords(), existingStatistics.getTotalRecords());
        assertEquals(argument.totalGrades(), existingStatistics.getTotalGrades());
        assertEquals(argument.averageGradeOfEntireStorage(), existingStatistics.getAverageGradeOfEntireStorage());
        assertEquals(argument.numberOfRecordsWithMaxAverageGrade(), existingStatistics.getNumberOfRecordsWithMaxAverageGrade());
        assertEquals(argument.percentageOfRecordsWithMaxAverageGrade(), existingStatistics.getPercentageOfRecordsWithMaxAverageGrade());
        assertEquals(argument.numberOfRecordsWithAverageGradeFourOrHigher(), existingStatistics.getNumberOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(argument.percentageOfRecordsWithAverageGradeFourOrHigher(), existingStatistics.getPercentageOfRecordsWithAverageGradeFourOrHigher());
        assertEquals(argument.numberOfRecordsWithoutGradesBelowFour(), existingStatistics.getNumberOfRecordsWithoutGradesBelowFour());
        assertEquals(argument.percentageOfRecordsWithoutGradesBelowFour(), existingStatistics.getPercentageOfRecordsWithoutGradesBelowFour());
        assertEquals(argument.numberOfRecordsWithoutGrades(), existingStatistics.getNumberOfRecordsWithoutGrades());
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
