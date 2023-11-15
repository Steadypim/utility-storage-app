package dev.steadypim.thewhitehw.homework1.repository.utilityStorage;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UtilityStorageRepositoryImpl implements UtilityStorageRepository{
    private final Map<Integer, UtilityStorage> storage;
    private final AtomicInteger idCounter;

    public UtilityStorageRepositoryImpl() {
        this.storage = new HashMap<>();
        int maxId = getMaxRecordId();
        idCounter = new AtomicInteger(maxId + 1);
    }

    @Override
    public UtilityStorage findById(int id) {
        return storage.get(id);
    }

    @Override
    public Page<UtilityStorage> findAllByNameCaseInsensitive(String name, Pageable pageable) {
        String lowerCaseName = name.toLowerCase();
        List<UtilityStorage> allRecords = new ArrayList<>(storage.values());
        List<UtilityStorage> filteredRecords = allRecords.stream()
                .filter(record -> record.getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredRecords.size());

        return new PageImpl<>(filteredRecords.subList(start, end), pageable, filteredRecords.size());
    }

    @Override
    public UtilityStorage create(UtilityStorage record) {
        int id = idCounter.getAndIncrement();
        record.setId(id);
        storage.put(id, record);
        return storage.get(record.getId());
    }

    @Override
    public void delete(UtilityStorage record) {
        storage.remove(record.getId());
    }

    public void update(UtilityStorage record, int id){
        UtilityStorage existingRecord = storage.get(id);

        existingRecord.setName(record.getName());
        existingRecord.setDescription(record.getDescription());
        existingRecord.setLink(record.getLink());

        storage.put(id, existingRecord);
    }

    public int getMaxRecordId(){
        int maxId = 0;
        for(UtilityStorage record : storage.values()){
            if(record.getId() > maxId){
                maxId = record.getId();
            }
        }
        return maxId;
    }
}
