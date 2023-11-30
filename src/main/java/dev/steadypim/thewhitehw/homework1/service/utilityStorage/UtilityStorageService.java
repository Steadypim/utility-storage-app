package dev.steadypim.thewhitehw.homework1.service.utilityStorage;

import com.querydsl.core.BooleanBuilder;
import dev.steadypim.thewhitehw.homework1.entity.QUtilityStorage;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilityStorage.UtilityStorageRepository;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument.UpdateUtilityRecordArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageService {
    private final UtilityStorageRepository repository;

    public UtilityStorage findRecordById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись не найдена"));
    }

    public Page<UtilityStorage> searchRecords(String name, String description, String sortField, String sortDirection, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(QUtilityStorage.utilityStorage.name.equalsIgnoreCase(name));
        }

        if (description != null) {
            predicate.and(QUtilityStorage.utilityStorage.description.eq(description));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return repository.findAll(predicate, pageable);
    }

    public UtilityStorage createRecord(CreateUtilityRecordArgument argument) {
        UtilityStorage record = UtilityStorage.builder()
                .name(argument.getName())
                .link(argument.getLink())
                .description(argument.getDescription())
                .build();
        return repository.save(record);
    }

    public void deleteRecordById(int id) {
        repository.delete(repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Запись не найдена")));
    }

    public void updateRecordById(UpdateUtilityRecordArgument dto, int id) {
        Optional<UtilityStorage> optionalStorage = repository.findById(id);
        if (optionalStorage.isPresent()) {
            UtilityStorage existingRecord = optionalStorage.get();
            existingRecord.setName(dto.getName());
            existingRecord.setDescription(dto.getDescription());
            existingRecord.setLink(dto.getLink());
            repository.save(existingRecord);
        }else {
            throw new EntityNotFoundException("Запись не найдена");
        }
    }
}
