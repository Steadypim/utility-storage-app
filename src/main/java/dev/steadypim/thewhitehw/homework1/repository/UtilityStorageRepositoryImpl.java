package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UtilityStorageRepositoryImpl implements UtilityStorageRepository{
    private final UtilityStorage utilityStorage;

    public UtilityStorageRepositoryImpl(@NonNull UtilityStorage utilityStorage) {
        this.utilityStorage = utilityStorage;
    }

    @Override
    public UtilityRecord findByIdOrNull(@NonNull int id) {
        return utilityStorage.getStorage().get(id);
    }

    @Override
    public List<UtilityRecord> findAllByNameCaseInsensitive(String name) {
        String lowerCaseName = name.toLowerCase();
        return utilityStorage.getStorage().values().stream()
                .filter(record -> record.getName().toLowerCase()
                        .contains(lowerCaseName))
                .collect(Collectors.toList());
    }
}
