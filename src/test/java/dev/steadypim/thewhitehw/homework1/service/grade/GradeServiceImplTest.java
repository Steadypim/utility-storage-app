package dev.steadypim.thewhitehw.homework1.service.grade;

import com.querydsl.core.types.Predicate;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.repository.grade.GradeRepository;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.SearchGradeArgument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeServiceImpl gradeService;

    @Test
    void testCreateWhenValidArgumentsThenGradeCreated() {
        // Arrange
        UtilityStorage mockUtilityStorage = new UtilityStorage();
        CreateGradeArgument argument = CreateGradeArgument.builder()
                .utilityStorage(mockUtilityStorage)
                .grade(5)
                .comment("Excellent")
                .build();
        Grade expectedGrade = Grade.builder()
                .utilityStorage(mockUtilityStorage)
                .grade(argument.getGrade())
                .comment(argument.getComment())
                .build();
        when(gradeRepository.save(any(Grade.class))).thenReturn(expectedGrade);

        // Act
        Grade result = gradeService.create(argument);

        // Assert
        verify(gradeRepository).save(any(Grade.class));
        assertNotNull(result);
        assertEquals(expectedGrade.getGrade(), result.getGrade());
        assertEquals(expectedGrade.getComment(), result.getComment());
        assertEquals(expectedGrade.getUtilityStorage(), result.getUtilityStorage());
    }

    @Test
    void testDeleteWhenValidIdThenGradeDeleted() {
        // Arrange
        int validId = 1;

        // Act
        gradeService.delete(validId);

        // Assert
        verify(gradeRepository).deleteById(validId);
    }

    @Test
    void testSearchGradesWhenValidArgumentsThenGradesFound() {
        // Arrange
        Integer recordId = 1;
        Integer gradeValue = 5;
        Pageable pageable = PageRequest.of(0, 10); // Replace with valid page number and size
        Page<Grade> expectedPage = mock(Page.class);
        when(gradeRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(expectedPage);

        SearchGradeArgument argument = SearchGradeArgument.builder()
                .recordId(recordId)
                .grade(gradeValue)
                .pageable(pageable)
                .build();

        // Act
        Page<Grade> result = gradeService.searchGrades(argument);

        // Assert
        verify(gradeRepository).findAll(any(Predicate.class), any(Pageable.class));
        assertNotNull(result);
        assertEquals(expectedPage, result);
    }
}