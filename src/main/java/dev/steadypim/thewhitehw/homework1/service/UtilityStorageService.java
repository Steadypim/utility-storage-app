package dev.steadypim.thewhitehw.homework1.service;

import dev.steadypim.thewhitehw.homework1.api.dtos.UtilityRecordDTO;
import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.exception.UtilityRecordNotFoundException;
import dev.steadypim.thewhitehw.homework1.mapper.UtilityStorageMapper;
import dev.steadypim.thewhitehw.homework1.repository.UtilityStorageRepositoryImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервис хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageService {

    private final UtilityStorageRepositoryImpl repository;
    private final UtilityStorageMapper mapper;
    private AtomicInteger idCounter;

    @PostConstruct
    public void initializeCounter(){
        int maxId = repository.getMaxRecordId();
        idCounter = new AtomicInteger(maxId + 1);
    }

    public UtilityRecordDTO displayRecordById(int id) {
        return mapper.toDto(Optional.ofNullable(repository.findById(id))
                .orElseThrow(() -> new UtilityRecordNotFoundException("Запись не найдена")));
    }

    public Page<UtilityRecordDTO> displayRecordsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<UtilityRecord> records = repository.findAllByNameCaseInsensitive(name, pageable);
        List<UtilityRecordDTO> dto = mapper.toDtoList(records.getContent());
        return new PageImpl<>(dto, pageable, records.getTotalElements());
    }

    public UtilityRecordDTO createRecord(UtilityRecordDTO dto){
        int id = idCounter.getAndIncrement();
        UtilityRecord record = mapper.toEntity(dto);
        record.setId(id);
        return mapper.toDto(repository.create(record));
    }

    public void deleteRecordById(int id){
        repository.delete(Optional.ofNullable(repository.findById(id)).
                orElseThrow(() -> new UtilityRecordNotFoundException("Запись не найдена")));
    }

    public void updateRecordById(UtilityRecordDTO dto, int id){
        repository.update(mapper.toEntity(dto), id);
    }
}
