package dev.steadypim.thewhitehw.homework1.action.create.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeServiceImpl;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateGradeActionTest {

    @Mock
    private UtilityStorageService storageService;

    @Mock
    private GradeServiceImpl gradeService;

    @InjectMocks
    private CreateGradeAction createGradeAction;

    @Test
    void testCreateWhenRecordExistsThenReturnGrade() {
        // Arrange
        CreateGradeActionArgument argument = CreateGradeActionArgument.builder()
                .utilityStorageId(1)
                .comment("Excellent")
                .grade(5)
                .build();
        UtilityStorage mockRecord = new UtilityStorage();
        mockRecord.setId(1);
        when(storageService.findRecordById(argument.getUtilityStorageId())).thenReturn(mockRecord);
        Grade expectedGrade = new Grade();
        when(gradeService.create(any(CreateGradeArgument.class))).thenReturn(expectedGrade);

        // Act
        Grade result = createGradeAction.create(argument);

        // Assert
        assertNotNull(result, "Результат не должен быть пустым, если найдена валидная запись.");
        assertEquals(expectedGrade, result, "The returned grade should match the expected grade");
        verify(storageService).findRecordById(argument.getUtilityStorageId());
        verify(gradeService).create(any(CreateGradeArgument.class));
    }

    @Test
    void testCreateWhenRecordNotExistsThenThrowException() {
        // Arrange
        CreateGradeActionArgument argument = CreateGradeActionArgument.builder()
                .utilityStorageId(1)
                .comment("Excellent")
                .grade(5)
                .build();
        when(storageService.findRecordById(argument.getUtilityStorageId())).thenThrow(new EntityNotFoundException("not found"));

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> createGradeAction.create(argument),
                "EntityNotFoundException should be thrown when the record does not exist"
        );
        assertNotNull(thrown.getMessage(), "The exception message should not be null");
        verify(storageService).findRecordById(argument.getUtilityStorageId());
        verify(gradeService, never()).create(any(CreateGradeArgument.class));
    }
}