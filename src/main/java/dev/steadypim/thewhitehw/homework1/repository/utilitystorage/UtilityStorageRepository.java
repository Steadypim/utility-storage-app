package dev.steadypim.thewhitehw.homework1.repository.utilitystorage;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с данными в UtilityStorage
 */
@Repository
public interface UtilityStorageRepository extends JpaRepository<UtilityStorage, Integer>, QuerydslPredicateExecutor<UtilityStorage> {
    @Query("SELECT COUNT(u) FROM UtilityStorage u JOIN Grade g ON u.id = g.utilityStorage.id GROUP BY u HAVING AVG(g.grade) = 5")
    Long calculateNumberOfRecordsWithMaxAverageGrade();

    @Query("SELECT (COUNT(u) * 100.0 / ((SELECT COUNT(u2) FROM UtilityStorage u2))) FROM UtilityStorage u JOIN Grade g ON u.id = g.utilityStorage.id GROUP BY u HAVING AVG(g.grade) = 5")
    Double calculatePercentageOfRecordsWithMaxAverageGrade();

    @Query("SELECT COUNT(u) FROM UtilityStorage u JOIN Grade g ON u.id = g.utilityStorage.id GROUP BY u HAVING AVG(g.grade) >= 4")
    Long calculateNumberOfRecordsWithAverageGradeFourOrHigher();

    @Query("SELECT (COUNT(u) * 100.0 / ((SELECT COUNT(u2) FROM UtilityStorage u2))) FROM UtilityStorage u JOIN Grade g ON u.id = g.utilityStorage.id GROUP BY u HAVING AVG(g.grade) >= 4")
    Double calculatePercentageOfRecordsWithAverageGradeFourOrHigher();

    @Query("SELECT COUNT(u) FROM UtilityStorage u WHERE NOT EXISTS (SELECT g FROM Grade g WHERE u.id = g.utilityStorage.id AND g.grade < 4)")
    Long calculateRecordsWithoutGradesBelowFour();

    @Query("SELECT (COUNT(u) * 100.0 / ((SELECT COUNT(u2) FROM UtilityStorage u2))) FROM UtilityStorage u WHERE NOT EXISTS (SELECT g FROM Grade g WHERE u.id = g.utilityStorage.id AND g.grade < 4)")
    Double calculatePercentageRecordsWithoutGradesBelowFour();

    @Query("SELECT AVG(g.grade) FROM Grade g")
    Double calculateAverageGradeOfEntireStorage();

    @Query("SELECT COUNT(u) FROM UtilityStorage u WHERE NOT EXISTS (SELECT g FROM Grade g WHERE u.id = g.utilityStorage.id)")
    Long calculateNumberOfRecordsWithoutGrades();
}
