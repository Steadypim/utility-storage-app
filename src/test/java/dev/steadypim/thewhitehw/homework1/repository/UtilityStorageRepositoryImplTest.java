package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class UtilityStorageRepositoryImplTest {
    private UtilityStorageRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new UtilityStorageRepositoryImpl();
    }


    @Test
    void testFindById() {
        // Arrange
        int expectedId = 1;
        UtilityStorage expectedRecord = new UtilityStorage();
        expectedRecord.setId(expectedId);
        repository.create(expectedRecord);

        // Act
        UtilityStorage actualRecord = repository.findById(expectedId);

        // Assert
        assertNotNull(actualRecord);
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testFindByNonExistingId() {
        //Arrange
        int nonExistingId = 1;

        //Act
        UtilityStorage actualRecord = repository.findById(nonExistingId);

        //Assert
        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive() {
        //Arrange
        String existingName = "Test 1";
        UtilityStorage record1 = new UtilityStorage(1, "test 1", "", "");
        UtilityStorage record2 = new UtilityStorage(2, "Test 1", "", "");

        repository.create(record1);
        repository.create(record2);
        //Act
        Page<UtilityStorage> records = repository.findAllByNameCaseInsensitive(existingName, PageRequest.of(0, 10));

        //Assert
        assertEquals(records.getTotalElements(), 2);
        assertTrue(records.getContent().contains(record1));
        assertTrue(records.getContent().contains(record2));
    }

    @Test
    void testFindAllByNonExistingNameCaseInsensitive() {
        //Arrange
        String nonExistingName = "nonExisting";
        UtilityStorage record1 = new UtilityStorage(1, "test 1", "", "");
        UtilityStorage record2 = new UtilityStorage(2, "test 1", "", "");

        repository.create(record1);
        repository.create(record2);

        //Act
        Page<UtilityStorage> records = repository.findAllByNameCaseInsensitive(nonExistingName, PageRequest.of(0, 10));

        //Assert
        assertEquals(0, records.getTotalElements());
        assertTrue(records.getContent().isEmpty());
    }

}
