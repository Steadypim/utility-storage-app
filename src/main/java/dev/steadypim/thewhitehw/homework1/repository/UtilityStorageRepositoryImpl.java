package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UtilityStorageRepositoryImpl implements UtilityStorageRepository{
    private final UtilityStorage utilityStorage;

    public UtilityStorageRepositoryImpl(@NotBlank @Value("${data.file.path}") String filePath) {
        this.utilityStorage = new UtilityStorage(filePath);
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
