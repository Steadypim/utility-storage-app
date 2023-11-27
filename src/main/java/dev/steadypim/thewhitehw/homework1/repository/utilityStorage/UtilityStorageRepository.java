package dev.steadypim.thewhitehw.homework1.repository.utilityStorage;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с данными в UtilityStorage
 */
@Repository
public interface UtilityStorageRepository extends JpaRepository<UtilityStorage, Integer> {

    Page<UtilityStorage> findAllByNameIgnoreCase(String name, Pageable pageable);

    Page<UtilityStorage> findAllByDescription(String description, Pageable pageable);
}
