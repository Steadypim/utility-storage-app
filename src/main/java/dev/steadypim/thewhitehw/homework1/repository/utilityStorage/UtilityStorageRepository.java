package dev.steadypim.thewhitehw.homework1.repository.utilityStorage;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Репозиторий для работы с данными в UtilityStorage
 */
public interface UtilityStorageRepository {
    UtilityStorage findById(int id);

    Page<UtilityStorage> findAllByNameCaseInsensitive(String name, Pageable pageable);

    UtilityStorage create(UtilityStorage record);

    void delete(UtilityStorage record);
}
