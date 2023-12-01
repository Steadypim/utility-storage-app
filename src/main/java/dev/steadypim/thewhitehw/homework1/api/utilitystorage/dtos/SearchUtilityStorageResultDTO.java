package dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class SearchUtilityStorageResultDTO {
    List<UtilityRecordDTO> records;
    long totalElements;
}
