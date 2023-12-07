package dev.steadypim.thewhitehw.homework1.entity;

import dev.steadypim.thewhitehw.homework1.general.entitypk.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

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
    @Column(name = "link")
    @ElementCollection
    private List<String> links;
}

