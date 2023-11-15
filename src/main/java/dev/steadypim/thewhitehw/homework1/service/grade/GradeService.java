package dev.steadypim.thewhitehw.homework1.service.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;

import java.util.List;

/**
 * Сервис для работы с оценками
 */
public interface GradeService {
    Grade create(CreateGradeArgument argument);

    void delete(int id);

    List<Grade> findAllByRecordId(int recordId);
}
