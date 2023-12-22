package dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorageStatistics;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateUtilityStorageStatisticsArgument;

public interface UtilityStorageStatisticsService {
    void updateStatistics(UpdateUtilityStorageStatisticsArgument argument);
    UtilityStorageStatistics getStatistics();
}
