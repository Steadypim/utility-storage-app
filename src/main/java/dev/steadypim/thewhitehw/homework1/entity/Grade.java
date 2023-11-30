package dev.steadypim.thewhitehw.homework1.entity;

import dev.steadypim.thewhitehw.homework1.general.entitypk.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

/**
 * Оценка
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grade extends BaseEntity {
    @ManyToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "utility_storage_id", referencedColumnName = "id", updatable = false, nullable = false)
    private UtilityStorage utilityStorage;
    private int grade;
    private String comment;
}


