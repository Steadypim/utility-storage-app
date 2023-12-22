package dev.steadypim.thewhitehw.homework1.repository.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorageStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityStorageStatisticsRepository extends JpaRepository<UtilityStorageStatistics, Integer> {
    UtilityStorageStatistics findFirstByOrderByIdAsc();
}
