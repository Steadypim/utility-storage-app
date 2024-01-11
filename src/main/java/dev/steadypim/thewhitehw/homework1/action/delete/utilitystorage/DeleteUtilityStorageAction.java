package dev.steadypim.thewhitehw.homework1.action.delete.utilitystorage;

import dev.steadypim.thewhitehw.homework1.service.grade.GradeService;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DeleteUtilityStorageAction {
    UtilityStorageService utilityStorageService;
    GradeService gradeService;

    @Transactional
    public void delete(int id) {
        gradeService.deleteAllByUtilityStorageId(id);
        utilityStorageService.deleteRecordById(id);
    }
}
