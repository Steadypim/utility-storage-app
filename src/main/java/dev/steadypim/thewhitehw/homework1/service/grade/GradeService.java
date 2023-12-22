package dev.steadypim.thewhitehw.homework1.service.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.SearchGradeArgument;
import org.springframework.data.domain.Page;

/**
 * Сервис для работы с оценками
 */
public interface GradeService {
    Grade create(CreateGradeArgument argument);

    void delete(int id);

    void deleteAllByUtilityStorageId(int id);

    Page<Grade> searchGrades(SearchGradeArgument argument);

    Long getTotalGrades();
}
