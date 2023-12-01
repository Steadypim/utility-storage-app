package dev.steadypim.thewhitehw.homework1.api.grade.argiment;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class GradeSearchCriteriaArgument {
    Integer recordId;
    Integer grade;
}
