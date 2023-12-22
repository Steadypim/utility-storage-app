package dev.steadypim.thewhitehw.homework1.entity;

import dev.steadypim.thewhitehw.homework1.general.entitypk.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UtilityStorageStatistics extends BaseEntity {
    Long totalRecords;
    Long totalGrades;
    Double averageGradeOfEntireStorage;
    Long numberOfRecordsWithMaxAverageGrade;
    Double percentageOfRecordsWithMaxAverageGrade;
    Long numberOfRecordsWithAverageGradeFourOrHigher;
    Double percentageOfRecordsWithAverageGradeFourOrHigher;
    Long numberOfRecordsWithoutGradesBelowFour;
    Double percentageOfRecordsWithoutGradesBelowFour;
    Long numberOfRecordsWithoutGrades;
}
