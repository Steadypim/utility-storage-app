package dev.steadypim.thewhitehw.homework1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запись в хранилище
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilityRecord {
    private int id;
    private String name;
    private String description;
    private String link;
}

