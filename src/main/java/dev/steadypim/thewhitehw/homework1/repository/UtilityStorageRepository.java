package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;

import java.util.List;

/**
 * Репозиторий для работы с данными в UtilityStorage
 */
public interface UtilityStorageRepository {
    UtilityRecord findById(int id);
    List<UtilityRecord> findAllByNameCaseInsensitive(String name);
}
