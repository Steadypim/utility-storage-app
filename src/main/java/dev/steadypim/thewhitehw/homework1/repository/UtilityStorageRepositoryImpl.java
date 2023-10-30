package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;

import java.util.List;
import java.util.stream.Collectors;

public class UtilityStorageRepositoryImpl implements UtilityStorageRepository{
    private final UtilityStorage utilityStorage;

    public UtilityStorageRepositoryImpl(UtilityStorage utilityStorage) {
        this.utilityStorage = utilityStorage;
    }

    @Override
    public UtilityRecord findByIdOrNull(int id) {
        return utilityStorage.storage().get(id);
    }

    @Override
    public List<UtilityRecord> findAllByNameCaseInsensitive(String name) {
        String lowerCaseName = name.toLowerCase();
        return utilityStorage.storage().values().stream()
                .filter(record -> record.name().toLowerCase()
                        .contains(lowerCaseName))
                .collect(Collectors.toList());
    }
}
