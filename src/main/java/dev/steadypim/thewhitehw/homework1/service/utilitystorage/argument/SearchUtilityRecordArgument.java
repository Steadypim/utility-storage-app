package dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@FieldDefaults(level = PRIVATE)
public class SearchUtilityRecordArgument {
    String name;
    String description;
    Pageable pageable;
}

