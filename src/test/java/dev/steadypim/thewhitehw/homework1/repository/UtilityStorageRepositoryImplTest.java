package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UtilityStorageRepositoryImplTest {

    @Mock
    private UtilityStorage storage;

    @InjectMocks
    private UtilityStorageRepositoryImpl repository;

    private final UtilityRecord firstRecord = new UtilityRecord(1, "test 1", "test description 1", "https://test.com/1");
    private final UtilityRecord secondRecord = new UtilityRecord(2, "test 1", "test description 2", "https://test.com/2");
    private final UtilityRecord thirdRecord = new UtilityRecord(3, "test 3", "test description 3", "https://test.com/3");

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(storage.storage()).thenReturn(Map.of(1, firstRecord,
                2, secondRecord,
                3, thirdRecord));
    }

    @Test
    void testFindById() {
        //Arrange
        int existingId = 1;

        //Act
        UtilityRecord actualRecord = repository.findByIdOrNull(existingId);

        //Assert
        assertNotNull(actualRecord);
        assertEquals(actualRecord, firstRecord);
    }

    @Test
    void testFindByNonExistingId() {
        //Arrange
        int nonExistingId = 4;

        //Act
        UtilityRecord actualRecord = repository.findByIdOrNull(nonExistingId);

        //Assert
        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive() {
        //Arrange
        String existingName = "Test 1";

        //Act
        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(existingName);

        //Assert
        assertEquals(records.size(), 2);
        assertTrue(records.contains(firstRecord));
        assertTrue(records.contains(secondRecord));
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive() {
        //Arrange
        String nonExistingName = "nonExisting";

        //Act
        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(nonExistingName);

        //Assert
        assertNotNull(records);
        assertTrue(records.isEmpty());
    }
}
