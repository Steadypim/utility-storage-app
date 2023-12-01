package dev.steadypim.thewhitehw.homework1.service.utilitystorage;

import com.querydsl.core.BooleanBuilder;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepository;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.SearchUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.UpdateUtilityRecordArgument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilityStorageServiceTest {

    @Mock
    private UtilityStorageRepository repository;

    private UtilityStorageService service;

    @BeforeEach
    void setUp() {
        service = new UtilityStorageService(repository);
    }

    @Test
    void testFindRecordByIdWhenRecordExistsThenReturnRecord() {
        // Arrange
        int id = 1;
        UtilityStorage expectedRecord = new UtilityStorage();
        when(repository.findById(id)).thenReturn(Optional.of(expectedRecord));

        // Act
        UtilityStorage actualRecord = service.findRecordById(id);

        // Assert
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testFindRecordByIdWhenRecordDoesNotExistThenThrowException() {
        // Arrange
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.findRecordById(id));
    }

    @Test
    void testSearchRecordsWhenRecordsExistThenReturnPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        Page<UtilityStorage> expectedPage = new PageImpl<>(List.of(new UtilityStorage()));
        when(repository.findAll(any(BooleanBuilder.class), eq(pageable))).thenReturn(expectedPage);

        SearchUtilityRecordArgument argument = SearchUtilityRecordArgument.builder()
                .name("test")
                .description("testDescription")
                .pageable(pageable)
                .build();

        // Act
        Page<UtilityStorage> actualPage = service.searchRecords(argument);

        // Assert
        assertEquals(expectedPage, actualPage);
    }

    @Test
    void testSearchRecordsWhenNoRecordsExistThenReturnEmptyPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        Page<UtilityStorage> expectedPage = Page.empty();
        when(repository.findAll(any(BooleanBuilder.class), eq(pageable))).thenReturn(expectedPage);

        SearchUtilityRecordArgument argument = SearchUtilityRecordArgument.builder()
                .name("test")
                .description("testDescription")
                .pageable(pageable)
                .build();

        // Act
        Page<UtilityStorage> actualPage = service.searchRecords(argument);

        // Assert
        assertTrue(actualPage.isEmpty());
    }

    @Test
    void testCreateRecordThenReturnRecord() {
        // Arrange
        CreateUtilityRecordArgument argument = new CreateUtilityRecordArgument("testName", "testDesc", "testLink");
        UtilityStorage expectedRecord = new UtilityStorage();
        when(repository.save(any(UtilityStorage.class))).thenReturn(expectedRecord);

        // Act
        UtilityStorage actualRecord = service.createRecord(argument);

        // Assert
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testDeleteRecordByIdWhenRecordExistsThenNoException() {
        // Arrange
        int id = 1;
        UtilityStorage existingRecord = new UtilityStorage();
        when(repository.findById(id)).thenReturn(Optional.of(existingRecord));
        doNothing().when(repository).delete(existingRecord);

        // Act & Assert
        assertDoesNotThrow(() -> service.deleteRecordById(id));
    }

    @Test
    void testDeleteRecordByIdWhenRecordDoesNotExistThenThrowException() {
        // Arrange
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.deleteRecordById(id));
    }

    @Test
    void testUpdateRecordByIdWhenRecordExistsThenNoException() {
        // Arrange
        int id = 1;
        UpdateUtilityRecordArgument argument = new UpdateUtilityRecordArgument("updatedName", "updatedDesc", "updatedLink");
        UtilityStorage existingRecord = new UtilityStorage();
        when(repository.findById(id)).thenReturn(Optional.of(existingRecord));
        when(repository.save(any(UtilityStorage.class))).thenReturn(existingRecord);

        // Act & Assert
        assertDoesNotThrow(() -> service.updateRecordById(argument, id));
    }

    @Test
    void testUpdateRecordByIdWhenRecordDoesNotExistThenThrowException() {
        // Arrange
        int id = 1;
        UpdateUtilityRecordArgument argument = new UpdateUtilityRecordArgument("updatedName", "updatedDesc", "updatedLink");
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.updateRecordById(argument, id));
    }
}