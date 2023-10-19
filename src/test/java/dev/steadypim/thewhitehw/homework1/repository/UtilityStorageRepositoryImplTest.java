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
    private UtilityRecord record;

    @Mock
    private UtilityStorage storage;

    @Mock
    private List<UtilityRecord> records;

    private UtilityStorageRepositoryImpl repository;

    @BeforeEach
    void setup(){
        storage = new UtilityStorage("src/test/java/dev/steadypim/thewhitehw/homework1/test.json");
        repository = new UtilityStorageRepositoryImpl(storage);
    }

    @Test
    void testFindById(){
        int existingId = 1;
        record = repository.findById(existingId);

        assertNotNull(record);
        assertEquals(existingId, record.id());
    }

    @Test
    void testFindByNonExistingId(){
        int nonExistingId = Integer.MIN_VALUE;
        record = repository.findById(nonExistingId);

        assertNull(record);
    }

    @Test
    void testFindAllByNameCaseInsensitive(){
        String existingName = "Test 1";
        records = repository.findAllByNameCaseInsensitive(existingName);

        assertNotNull(records);
        assertFalse(records.isEmpty());
        for (UtilityRecord record : records) {
            assertTrue(record.name().toLowerCase().contains(existingName.toLowerCase()));
        }
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive(){
        String nonExistingName = "nonExisting";
        records = repository.findAllByNameCaseInsensitive(nonExistingName);

        assertNotNull(records);
        assertTrue(records.isEmpty());
    }
}
