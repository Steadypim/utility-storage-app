package dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorageStatistics;
import dev.steadypim.thewhitehw.homework1.repository.utilitystoragestatistics.UtilityStorageStatisticsRepository;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateUtilityStorageStatisticsArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис статистики хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageStatisticsServiceImpl implements UtilityStorageStatisticsService{

    private final UtilityStorageStatisticsRepository repository;
    @Override
    @Transactional
    public void updateStatistics(UpdateUtilityStorageStatisticsArgument argument) {
        UtilityStorageStatistics statistics = repository.findFirstByOrderByIdAsc();

        if(statistics == null){
            statistics = new UtilityStorageStatistics();
        }

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
    public UtilityStorageStatistics getStatistics() {
        UtilityStorageStatistics statistics = repository.findFirstByOrderByIdAsc();

        if (statistics == null){
            statistics = new UtilityStorageStatistics();
        }

        return statistics;
    }
}
