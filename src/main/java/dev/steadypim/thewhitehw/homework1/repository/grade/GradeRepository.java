package dev.steadypim.thewhitehw.homework1.repository.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с данными в Grade
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>, QuerydslPredicateExecutor<Grade> {
    void deleteAllByUtilityStorageId(int id);
}

