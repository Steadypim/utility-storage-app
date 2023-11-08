package dev.steadypim.thewhitehw.homework1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilityStorageTest {
    @Test
    void testLoadedDataNotNull() {
        //Arrange
        String filePath = "src/test/java/dev/steadypim/thewhitehw/homework1/entity/test.json";
        UtilityStorage utilityStorage = new UtilityStorage(filePath);

        //Act
        UtilityRecord record = utilityStorage.getStorage().get(1);

        //Assert
        assertFalse(utilityStorage.getStorage().isEmpty());

        assertEquals(1, record.getId());
        assertEquals("test 1", record.getName());
        assertEquals("test description 1", record.getDescription());
        assertEquals("https://test.com/1", record.getLink());
    }
}
