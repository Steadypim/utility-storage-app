package dev.steadypim.thewhitehw.homework1.service.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис для работы с оценками
 */
public interface GradeService {
    Grade create(CreateGradeArgument argument, UtilityStorage record);

    void delete(int id);

    Page<Grade> findAllByRecordId(int recordId, String sortField, String sortDirection, Pageable pageable);

    Page<Grade> findAllGradesByGrade(int grade, String sortField, String sortDirection, Pageable pageable);
}
