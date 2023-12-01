package dev.steadypim.thewhitehw.homework1.api.utilitystorage.argument;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class UtilityStorageSearchCriteriaArgument {
    String name;
    String description;
}
