package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

class UtilityStorageRepositoryImplTest {
    private UtilityStorageRepositoryImpl repository;

    private final UtilityRecord firstRecord = UtilityRecord.builder().id(1)
            .name("test 1").description("test description 1").link("https://test.com/1").build();
    private final UtilityRecord secondRecord = UtilityRecord.builder().id(2)
            .name("test 1").description("test description 2").link("https://test.com/2").build();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        repository = new UtilityStorageRepositoryImpl("C:\\code\\utility-storage-app\\src\\test\\java\\dev\\steadypim\\thewhitehw\\homework1\\test.json");
    }

    @Test
    void testFindById() {
        //Arrange
        int existingId = 1;

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

        //Act
        UtilityRecord actualRecord = repository.findById(nonExistingId);

        //Assert
        assertNull(actualRecord);
    }

    @Test
    void testFindAllByNameCaseInsensitive() {
        //Arrange
        String existingName = "Test 1";

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

        //Act
        Page<UtilityRecord> records = repository.findAllByNameCaseInsensitive(nonExistingName, PageRequest.of(0, 10));

        //Assert
        assertEquals(0, records.getTotalElements());
        assertTrue(records.getContent().isEmpty());
    }
}
