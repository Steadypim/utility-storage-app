package dev.steadypim.thewhitehw.homework1.service.grade.argument;

import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGradeArgument {
    private UtilityStorage utilityStorage;
    private int grade;
    private String comment;
}
