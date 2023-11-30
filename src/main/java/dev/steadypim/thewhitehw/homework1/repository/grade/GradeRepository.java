package dev.steadypim.thewhitehw.homework1.repository.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с данными в Grade
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Page<Grade> findAllByUtilityStorageId(int utilityStorageId, Pageable pageable);

    Page<Grade> findAllByGrade(int grade, Pageable pageable);
}

