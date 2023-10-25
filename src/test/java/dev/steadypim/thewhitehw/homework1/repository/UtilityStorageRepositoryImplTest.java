package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityStorageRepositoryImplTest {

    @Mock
    private UtilityStorage storage;

    private UtilityStorageRepositoryImpl repository;

    @BeforeEach
    void setup(){
        storage = new UtilityStorage();
        storage.storage().put(1, new UtilityRecord(1, "test 1", "test description 1", "https://test.com/1"));
        storage.storage().put(2, new UtilityRecord(1, "test 1", "test description 2", "https://test.com/2"));
        storage.storage().put(3, new UtilityRecord(1, "test 3", "test description 3", "https://test.com/3"));
        repository = new UtilityStorageRepositoryImpl(storage);
    }

    @Test
    void testFindById(){
        int existingId = 1;

        UtilityRecord expectedRecord = new UtilityRecord(1, "test 1", "test description 1", "https://test.com/1");

        UtilityRecord actualRecord = repository.findByIdOrNull(existingId);

        assertNotNull(actualRecord);
        assertEquals(expectedRecord.id(), actualRecord.id());
    }

    @Test
    void testFindByNonExistingId(){
        int nonExistingId = Integer.MIN_VALUE;
        UtilityRecord actualRecord = repository.findByIdOrNull(nonExistingId);

        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive(){
        String existingName = "Test 1";
        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(existingName);

        assertNotNull(records);
        assertFalse(records.isEmpty());
        for (UtilityRecord record : records) {
            assertTrue(record.name().toLowerCase().contains(existingName.toLowerCase()));
        }
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive(){
        String nonExistingName = "nonExisting";
        List<UtilityRecord> records = repository.findAllByNameCaseInsensitive(nonExistingName);

        assertNotNull(records);
        assertTrue(records.isEmpty());
    }
}
