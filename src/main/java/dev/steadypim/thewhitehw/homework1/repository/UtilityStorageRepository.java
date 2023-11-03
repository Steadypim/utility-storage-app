package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Репозиторий для работы с данными в UtilityStorage
 */
public interface UtilityStorageRepository {
    UtilityRecord findById(int id);

    Page<UtilityRecord> findAllByNameCaseInsensitive(String name, Pageable pageable);

    UtilityRecord create(UtilityRecord record);

    void delete(UtilityRecord record);
}
