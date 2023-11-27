package dev.steadypim.thewhitehw.homework1.entity;

import dev.steadypim.thewhitehw.homework1.general.entitypk.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * Запись в хранилище
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UtilityStorage extends BaseEntity {
    private String name;
    private String description;
    private String link;
}

