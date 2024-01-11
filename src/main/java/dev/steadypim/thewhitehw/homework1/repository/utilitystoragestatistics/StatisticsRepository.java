package dev.steadypim.thewhitehw.homework1.repository.utilitystoragestatistics;

import dev.steadypim.thewhitehw.homework1.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
    Statistics findFirstByOrderByIdAsc();
}
