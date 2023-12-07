package dev.steadypim.thewhitehw.homework1.action.create.grade;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateGradeActionArgument {
    int utilityStorageId;
    String comment;
    int grade;
}
