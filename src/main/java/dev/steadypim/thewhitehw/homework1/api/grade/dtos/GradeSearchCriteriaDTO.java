package dev.steadypim.thewhitehw.homework1.api.grade.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class GradeSearchCriteriaDTO {
    @NotNull(message = "id записи не указан")
    Integer recordId;
    Integer grade;
}
