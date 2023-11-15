package dev.steadypim.thewhitehw.homework1.service.utilityStorage;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepositoryImpl;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.UpdateUtilityRecordArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageService {
    private final UtilityStorageRepositoryImpl repository;

    public UtilityStorage findRecordById(int id) {
        return Optional.ofNullable(repository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Запись не найдена"));
    }

    public Page<UtilityStorage> findAllRecordsByName(String name, Pageable pageable) {
        return repository.findAllByNameCaseInsensitive(name, pageable);
    }

    public UtilityStorage createRecord(CreateUtilityRecordArgument argument){
        UtilityStorage record = UtilityStorage.builder()
                .name(argument.getName())
                .link(argument.getLink())
                .description(argument.getDescription())
                .build();
        return repository.create(record);
    }

    public void deleteRecordById(int id){
        repository.delete(Optional.ofNullable(repository.findById(id)).
                orElseThrow(() -> new EntityNotFoundException("Запись не найдена")));
    }

    public void updateRecordById(UpdateUtilityRecordArgument dto, int id){
        UtilityStorage record = UtilityStorage.builder()
                .id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .link(dto.getLink())
                .build();
        repository.update(record, id);
    }
}
