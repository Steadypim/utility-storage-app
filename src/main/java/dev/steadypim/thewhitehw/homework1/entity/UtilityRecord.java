package dev.steadypim.thewhitehw.homework1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//Todo изменил рекорды на классы, т.к рекорды не получилось в DI добавить,
// требовал конструкторы, проще было Lombok юзать.
/**
 * Запись в хранилище
 */
@Component
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

