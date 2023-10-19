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
    public UtilityRecord findById(int id) {
        return utilityStorage.storage().get(id);
//todo        return Optional.ofNullable(utilityStorage.storage().get(id))
//                .orElseThrow(() -> new RuntimeException("No such record with id: " + id));
//                мб так можно сделать, но тогда приложение завершается, а не возвращается в меню к в ТЗ
    }

    @Override
    public List<UtilityRecord> findAllByNameCaseInsensitive(String name) {
        return utilityStorage.storage().values().stream()
                .filter(record -> record.name().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
