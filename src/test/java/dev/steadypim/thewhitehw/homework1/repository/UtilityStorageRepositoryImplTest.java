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
        int existingId = 1;

        UtilityRecord actualRecord = repository.findByIdOrNull(existingId);

        assertNotNull(actualRecord);
        assertEquals(actualRecord, firstRecord);
    }

    @Test
    void testFindByNonExistingId() {
        int nonExistingId = 4;

        UtilityRecord actualRecord = repository.findByIdOrNull(nonExistingId);

        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive() {
        String existingName = "Test 1";

        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(existingName);

        assertEquals(records.size(), 2);
        for (UtilityRecord record : records) {
            assertTrue(record.name().toLowerCase().contains(existingName.toLowerCase()));
        }
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive() {
        String nonExistingName = "nonExisting";
        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(nonExistingName);

        assertNotNull(records);
        assertTrue(records.isEmpty());
    }
}
