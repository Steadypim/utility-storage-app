package dev.steadypim.thewhitehw.homework1.repository.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;

import java.util.List;

/**
 * Репозиторий для работы с данными в Grade
 */
public interface GradeRepository {
    Grade create(Grade grade);

    List<Grade> findAllByRecordId(int recordId);

    void delete(int id);
}
