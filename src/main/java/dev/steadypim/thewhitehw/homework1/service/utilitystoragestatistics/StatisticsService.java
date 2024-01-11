package dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.Statistics;
import dev.steadypim.thewhitehw.homework1.service.utilitystoragestatistics.argument.UpdateStatisticsArgument;

public interface StatisticsService {
    void updateStatistics(UpdateStatisticsArgument argument);

    Statistics getStatistics();
}
