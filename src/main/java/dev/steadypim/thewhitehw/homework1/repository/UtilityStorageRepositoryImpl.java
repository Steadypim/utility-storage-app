package dev.steadypim.thewhitehw.homework1.repository;

import dev.steadypim.thewhitehw.homework1.entity.UtilityRecord;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UtilityStorageRepositoryImpl implements UtilityStorageRepository{
    private UtilityStorage utilityStorage;
    private final String filePath;
    public UtilityStorageRepositoryImpl(@Value("${data.file.path}") String filePath) {
        this.utilityStorage = null;
        this.filePath = filePath;
    }

    @PostConstruct
    private void initializeUtilityStorage() {
        this.utilityStorage = new UtilityStorage(filePath);
    }

    @Override
    public UtilityRecord findById(int id) {
        return utilityStorage.getStorage().get(id);
    }

    @Override
    public Page<UtilityRecord> findAllByNameCaseInsensitive(String name, Pageable pageable) {
        String lowerCaseName = name.toLowerCase();
        List<UtilityRecord> allRecords = new ArrayList<>(utilityStorage.getStorage().values());
        List<UtilityRecord> filteredRecords = allRecords.stream()
                .filter(record -> record.getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredRecords.size());

        return new PageImpl<>(filteredRecords.subList(start, end), pageable, filteredRecords.size());
    }

    @Override
    public UtilityRecord create(UtilityRecord record) {
        utilityStorage.getStorage().put(record.getId(), record);
        return utilityStorage.getStorage().get(record.getId());
    }

    @Override
    public void delete(UtilityRecord record) {
        utilityStorage.getStorage().remove(record.getId());
    }

    public void update(UtilityRecord record, int id){
        UtilityRecord existingRecord = utilityStorage.getStorage().get(id);

        existingRecord.setName(record.getName());
        existingRecord.setDescription(record.getDescription());
        existingRecord.setLink(record.getLink());

        utilityStorage.getStorage().put(id, existingRecord);
    }

    public int getMaxRecordId(){
        int maxId = 0;
        for(UtilityRecord record : utilityStorage.getStorage().values()){
            if(record.getId() > maxId){
                maxId = record.getId();
            }
        }
        return maxId;
    }
}
