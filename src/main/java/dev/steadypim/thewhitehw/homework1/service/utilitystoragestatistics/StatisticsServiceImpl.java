package dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.Statistics;
import dev.steadypim.thewhitehw.homework1.repository.utilitystoragestatistics.StatisticsRepository;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateStatisticsArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис статистики хранилища
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository repository;

    @Override
    @Transactional
    public void updateStatistics(UpdateStatisticsArgument argument) {
        Statistics statistics = Optional.ofNullable(repository.findFirstByOrderByIdAsc())
                                        .orElseGet(Statistics::new);

        statistics.setTotalRecords(argument.totalRecords());
        statistics.setTotalGrades(argument.totalGrades());
        statistics.setAverageGradeOfEntireStorage(argument.averageGradeOfEntireStorage());
        statistics.setNumberOfRecordsWithMaxAverageGrade(argument.numberOfRecordsWithMaxAverageGrade());
        statistics.setPercentageOfRecordsWithMaxAverageGrade(argument.percentageOfRecordsWithMaxAverageGrade());
        statistics.setNumberOfRecordsWithAverageGradeFourOrHigher(argument.numberOfRecordsWithAverageGradeFourOrHigher());
        statistics.setPercentageOfRecordsWithAverageGradeFourOrHigher(argument.percentageOfRecordsWithAverageGradeFourOrHigher());
        statistics.setNumberOfRecordsWithoutGradesBelowFour(argument.numberOfRecordsWithoutGradesBelowFour());
        statistics.setPercentageOfRecordsWithoutGradesBelowFour(argument.percentageOfRecordsWithoutGradesBelowFour());
        statistics.setNumberOfRecordsWithoutGrades(argument.numberOfRecordsWithoutGrades());

        repository.save(statistics);
    }

    @Override
    @Transactional(readOnly = true)
    public Statistics getStatistics() {
        Statistics statistics = Optional.ofNullable(repository.findFirstByOrderByIdAsc())
                                        .orElseGet(Statistics::new);

        return statistics;
    }
}
