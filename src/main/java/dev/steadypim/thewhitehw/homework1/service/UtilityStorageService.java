package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import dev.steadypim.thewhitehw.homework1.service.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.argument.UpdateUtilityRecordArgument;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервис хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageService {
    private final UtilityStorageRepositoryImpl repository;
    private AtomicInteger idCounter;

    @PostConstruct
    public void initializeCounter(){
        int maxId = repository.getMaxRecordId();
        idCounter = new AtomicInteger(maxId + 1);
    }

    public UtilityRecord displayRecordById(int id) {
        return Optional.ofNullable(repository.findById(id))
                .orElseThrow(() -> new UtilityRecordNotFoundException("Запись не найдена"));
    }

    public Page<UtilityRecord> displayRecordsByName(String name, Pageable pageable) {
        return repository.findAllByNameCaseInsensitive(name, pageable);
    }

    public UtilityRecord createRecord(CreateUtilityRecordArgument argument){
        int id = idCounter.getAndIncrement();
        UtilityRecord record = UtilityRecord.builder()
                .id(id)
                .name(argument.getName())
                .link(argument.getLink())
                .description(argument.getDescription())
                .build();
        return repository.create(record);
    }

    public void deleteRecordById(int id){
        repository.delete(Optional.ofNullable(repository.findById(id)).
                orElseThrow(() -> new UtilityRecordNotFoundException("Запись не найдена")));
    }

    public void updateRecordById(UpdateUtilityRecordArgument dto, int id){
        UtilityRecord record = UtilityRecord.builder()
                .id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .link(dto.getLink())
                .build();
        repository.update(record, id);
    }
}
