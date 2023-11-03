package dev.steadypim.thewhitehw.homework1.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранилище записей
 */
@NoArgsConstructor
@Data
public class UtilityStorage{

    private Map<Integer, UtilityRecord> storage;
    public UtilityStorage(String filePath) {
        this.storage = loadRecordsFromFile(filePath);
    }

    private static Map<Integer, UtilityRecord> loadRecordsFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<UtilityRecord> records = objectMapper.readValue(new File(filePath), new TypeReference<>() {
            });
            Map<Integer, UtilityRecord> storage = new HashMap<>();
            for (UtilityRecord record : records) {
                storage.put(record.getId(), record);
            }
            return storage;
        } catch (IOException e) {
            System.out.println("Ошибка при считывании записей из файла, catch: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
