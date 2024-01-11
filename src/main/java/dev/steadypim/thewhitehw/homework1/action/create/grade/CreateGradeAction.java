package dev.steadypim.thewhitehw.homework1.action.create.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeServiceImpl;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.utilitystorage.UtilityStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateGradeAction {
    private final UtilityStorageService storageService;
    private final GradeServiceImpl gradeService;

    @Transactional
    public Grade create(CreateGradeActionArgument argument) {
        UtilityStorage record = storageService.findRecordById(argument.getUtilityStorageId());

        CreateGradeArgument createGradeArgument = CreateGradeArgument.builder()
                                                                     .comment(argument.getComment())
                                                                     .utilityStorage(record)
                                                                     .grade(argument.getGrade())
                                                                     .build();

        return gradeService.create(createGradeArgument);
    }
}
