package dev.steadypim.thewhitehw.homework1.action.delete.utilitystorage;

import dev.steadypim.thewhitehw.homework1.action.gelete.utilitystorage.DeleteUtilityStorageAction;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeService;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE)
class DeleteUtilityStorageActionTest {

    @Mock
    UtilityStorageService utilityStorageService;

    @Mock
    GradeService gradeService;

    @InjectMocks
    DeleteUtilityStorageAction deleteUtilityStorageAction;

    @Test
    void testDeleteWhenIdExistsThenDeleteRecord() {
        // Arrange
        int existingId = 1;
        doNothing().when(gradeService).deleteAllByUtilityStorageId(existingId);
        doNothing().when(utilityStorageService).deleteRecordById(existingId);

        // Act
        deleteUtilityStorageAction.delete(existingId);

        // Assert
        verify(gradeService).deleteAllByUtilityStorageId(existingId);
        verify(utilityStorageService).deleteRecordById(existingId);
    }

    @Test
    void testDeleteWhenIdDoesNotExistThenDoNothing() {
        // Arrange
        int nonExistingId = 2;
        doNothing().when(gradeService).deleteAllByUtilityStorageId(nonExistingId);
        doThrow(new EntityNotFoundException("Запись не найдена")).when(utilityStorageService).deleteRecordById(nonExistingId);

        // Act & Assert
        deleteUtilityStorageAction.delete(nonExistingId);

        verify(gradeService).deleteAllByUtilityStorageId(nonExistingId);
        verify(utilityStorageService).deleteRecordById(nonExistingId);
    }
}