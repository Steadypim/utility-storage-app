package dev.steadypim.thewhitehw.homework1.service.utilitystorage;

import com.querydsl.core.BooleanBuilder;
import dev.steadypim.thewhitehw.homework1.annotation.MethodForStatistics;
import dev.steadypim.thewhitehw.homework1.entity.QUtilityStorage;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.utilitystorage.UtilityStorageRepository;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.CreateUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.SearchUtilityRecordArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument.UpdateUtilityRecordArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис хранилища
 */
@Service
@RequiredArgsConstructor
public class UtilityStorageService {
    private final UtilityStorageRepository repository;

    @Transactional(readOnly = true)
    public UtilityStorage findRecordById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись не найдена"));
    }

    @Transactional(readOnly = true)
    public Page<UtilityStorage> searchRecords(SearchUtilityRecordArgument argument) {
        QUtilityStorage qUtilityStorage = QUtilityStorage.utilityStorage;
        BooleanBuilder predicate = new BooleanBuilder();

        if (argument.getName() != null) {
            predicate.and(qUtilityStorage.name.equalsIgnoreCase(argument.getName()));
        }

        if (argument.getDescription() != null) {
            predicate.and(qUtilityStorage.description.eq(argument.getDescription()));
        }


        return repository.findAll(predicate, argument.getPageable());
    }

    @Transactional
    @MethodForStatistics
    public UtilityStorage createRecord(CreateUtilityRecordArgument argument) {
        UtilityStorage record = UtilityStorage.builder()
                .name(argument.getName())
                .links(argument.getLinks())
                .description(argument.getDescription())
                .build();
        return repository.save(record);
    }

    @Transactional
    @MethodForStatistics
    public void deleteRecordById(int id) {
        repository.delete(repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Запись не найдена")));
    }

    @Transactional
    public void updateRecordById(UpdateUtilityRecordArgument dto, int id) {
        UtilityStorage existingRecord = findRecordById(id);

        existingRecord.setName(dto.getName());
        existingRecord.setDescription(dto.getDescription());
        existingRecord.setLinks(dto.getLinks());
        repository.save(existingRecord);
    }

    @Transactional(readOnly = true)
    public Long getTotalRecords(){
        return repository.count();
    }

    @Transactional(readOnly = true)
    public Long calculateNumberOfRecordsWithMaxAverageGrade(){
        return repository.calculateNumberOfRecordsWithMaxAverageGrade();
    }

    @Transactional(readOnly = true)
    public Double calculatePercentageOfRecordsWithMaxAverageGrade(){
        return repository.calculatePercentageOfRecordsWithMaxAverageGrade();
    }

    @Transactional(readOnly = true)
    public Long calculateNumberOfRecordsWithAverageGradeFourOrHigher(){
        return repository.calculateNumberOfRecordsWithAverageGradeFourOrHigher();
    }

    @Transactional(readOnly = true)
    public Double calculatePercentageOfRecordsWithAverageGradeFourOrHigher(){
        return repository.calculatePercentageOfRecordsWithAverageGradeFourOrHigher();
    }

    @Transactional(readOnly = true)
    public Long calculateRecordsWithoutGradesBelowFour(){
        return repository.calculateRecordsWithoutGradesBelowFour();
    }

    @Transactional(readOnly = true)
    public Double calculatePercentageRecordsWithoutGradesBelowFour(){
        return repository.calculatePercentageRecordsWithoutGradesBelowFour();
    }

    @Transactional(readOnly = true)
    public Double calculateAverageGradeOfEntireStorage(){
        return repository.calculateAverageGradeOfEntireStorage();
    }

    @Transactional(readOnly = true)
    public Long calculateNumberOfRecordsWithoutGrades(){
        return repository.calculateNumberOfRecordsWithoutGrades();
    }
}
