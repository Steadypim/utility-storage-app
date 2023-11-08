package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UtilityStorageRepositoryImplTest {
    private UtilityStorageRepositoryImpl repository;

    @Mock
    private UtilityStorage utilityStorage;

    private final UtilityRecord firstRecord = UtilityRecord
            .builder()
            .id(1)
            .name("test 1")
            .description("test description 1")
            .link("https://test.com/1")
            .build();
    private final UtilityRecord secondRecord = UtilityRecord
            .builder()
            .id(2)
            .name("test 1")
            .description("test description 2")
            .link("https://test.com/2")
            .build();

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        repository = new UtilityStorageRepositoryImpl("");
        setField(repository, "utilityStorage", utilityStorage);
    }

    @Test
    void testFindById() {
        //Arrange
        int existingId = 1;
        Map<Integer, UtilityRecord> storage = new HashMap<>();
        storage.put(existingId, firstRecord);
        when(utilityStorage.getStorage()).thenReturn(storage);

        //Act
        UtilityRecord actualRecord = repository.findById(existingId);

        //Assert
        assertNotNull(actualRecord);
        assertEquals(actualRecord, firstRecord);
    }

    @Test
    void testFindByNonExistingId() {
        //Arrange
        int nonExistingId = 4;
        Map<Integer, UtilityRecord> storage = new HashMap<>();
        when(utilityStorage.getStorage()).thenReturn(storage);

        //Act
        UtilityRecord actualRecord = repository.findById(nonExistingId);

        //Assert
        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive() {
        //Arrange
        String existingName = "Test 1";
        Map<Integer, UtilityRecord> storage = new HashMap<>();
        storage.put(1, firstRecord);
        storage.put(2, secondRecord);
        when(utilityStorage.getStorage()).thenReturn(storage);

        //Act
        Page<UtilityRecord> records = repository.findAllByNameCaseInsensitive(existingName, PageRequest.of(0, 10));

        //Assert
        assertEquals(records.getTotalElements(), 2);
        assertTrue(records.getContent().contains(firstRecord));
        assertTrue(records.getContent().contains(secondRecord));
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive() {
        //Arrange
        String nonExistingName = "nonExisting";
        Map<Integer, UtilityRecord> storage = new HashMap<>();
        when(utilityStorage.getStorage()).thenReturn(storage);

        //Act
        Page<UtilityRecord> records = repository.findAllByNameCaseInsensitive(nonExistingName, PageRequest.of(0, 10));

        //Assert
        assertEquals(0, records.getTotalElements());
        assertTrue(records.getContent().isEmpty());
    }

    private void setField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
