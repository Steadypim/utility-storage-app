package dev.steadypim.thewhitehw.homework1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilityStorageTest {
    @Test
    void testLoadedDataNotNull() {
        String filePath = "src/test/java/dev/steadypim/thewhitehw/homework1/test.json";

        UtilityStorage utilityStorage = new UtilityStorage(filePath);
        UtilityRecord record = utilityStorage.storage().get(1);

        assertFalse(utilityStorage.storage().isEmpty());

        assertEquals(1, record.id());
        assertEquals("test 1", record.name());
        assertEquals("test description 1", record.description());
        assertEquals("https://test.com/1", record.link());
    }
}
