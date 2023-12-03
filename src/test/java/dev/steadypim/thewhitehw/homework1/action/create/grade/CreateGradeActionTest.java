package dev.steadypim.thewhitehw.homework1.action.create.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeServiceImpl;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateGradeActionTest {

    @Mock
    private UtilityStorageService storageService;

    @Mock
    private GradeServiceImpl gradeService;

    @Mock
    UtilityStorage mockRecord;

    @InjectMocks
    private CreateGradeAction createGradeAction;

    @Test
    void testCreateWhenRecordExistsThenReturnGrade() {
        // Arrange
        CreateGradeActionArgument argument = CreateGradeActionArgument.builder()
                .utilityStorageId(0)
                .comment("Excellent")
                .grade(5)
                .build();
        mockRecord = new UtilityStorage();
        when(storageService.findRecordById(argument.getUtilityStorageId())).thenReturn(mockRecord);
        Grade expectedGrade = new Grade();
        when(gradeService.create(any(CreateGradeArgument.class))).thenReturn(expectedGrade);

        ArgumentCaptor<CreateGradeArgument> captor = ArgumentCaptor.forClass(CreateGradeArgument.class);

        // Act
        Grade result = createGradeAction.create(argument);

        // Assert
        assertNotNull(result, "Result should not be empty if record is valid");
        assertEquals(expectedGrade, result, "The returned grade should match the expected grade");
        verify(storageService).findRecordById(argument.getUtilityStorageId());
        verify(gradeService).create(captor.capture());

        CreateGradeArgument capturedArgument = captor.getValue();
        assertEquals(argument.getUtilityStorageId(), capturedArgument.getUtilityStorage().getId(), "Utility Storage ID should match");
        assertEquals(argument.getComment(), capturedArgument.getComment(), "Comment should match");
        assertEquals(argument.getGrade(), capturedArgument.getGrade(), "Grade should match");
    }
}