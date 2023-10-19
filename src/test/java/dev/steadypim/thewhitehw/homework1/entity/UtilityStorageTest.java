package dev.steadypim.thewhitehw.homework1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilityStorageTest {
    @Test
    void testLoadedDataNotNull() {
        String filePath = "src/test/java/dev/steadypim/thewhitehw/homework1/test.json";

        UtilityStorage utilityStorage = new UtilityStorage(filePath);

        assertFalse(utilityStorage.storage().isEmpty());
    }
}
