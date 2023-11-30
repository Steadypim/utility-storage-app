package dev.steadypim.thewhitehw.homework1.action.create.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeServiceImpl;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.utilityStorage.UtilityStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class CreateGradeAction {
    private final UtilityStorageService storageService;
    private final GradeServiceImpl gradeService;

    public Grade create(CreateGradeActionArgument argument) {
        UtilityStorage record = storageService.findRecordById(argument.getUtilityStorageId());

        return gradeService.create(CreateGradeArgument.builder()
                .comment(argument.getComment())
                .utilityStorageId(record.getId())
                .grade(argument.getGrade())
                .build(),record);
    }
}
