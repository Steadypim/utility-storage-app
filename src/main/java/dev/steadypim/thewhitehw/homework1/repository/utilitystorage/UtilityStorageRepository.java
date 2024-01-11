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
    @Query("SELECT COUNT(*) " +
           "FROM UtilityStorage gs " +
           "WHERE 5 = (SELECT AVG(g.grade) FROM Grade g WHERE g.utilityStorage = gs)")
    Long countRecordsWithAverageGradeEqualsFive();


    @Query("SELECT COUNT(*) " +
           "FROM UtilityStorage gs " +
           "WHERE 4 <= (SELECT AVG(g.grade) FROM Grade g WHERE g.utilityStorage = gs)")
    Long countRecordsWithAverageGradeEqualsFourOrHigher();

    @Query("SELECT COUNT(*) " +
           "FROM UtilityStorage gs " +
           "WHERE NOT EXISTS (" +
           "    SELECT g FROM Grade g WHERE g.utilityStorage = gs AND g.grade < 4" +
           ")")
    Long countRecordsWithoutGradesBelowFour();

    @Query("SELECT COUNT(*) FROM UtilityStorage gs WHERE NOT EXISTS (SELECT g FROM Grade g WHERE g.utilityStorage = gs)")
    Long countRecordsWithoutGrades();
}
