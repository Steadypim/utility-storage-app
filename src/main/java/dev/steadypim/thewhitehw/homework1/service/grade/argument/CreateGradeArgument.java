package dev.steadypim.thewhitehw.homework1.service.grade.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGradeArgument {
    private int recordId;
    private int grade;
    private String comment;
}
