package dev.steadypim.thewhitehw.homework1.api.grade;

import dev.steadypim.thewhitehw.homework1.action.create.grade.CreateGradeAction;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.CreateGradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.dtos.GradeDTO;
import dev.steadypim.thewhitehw.homework1.api.grade.mapper.GradeMapper;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.service.grade.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для работы с оценками")
@RestController
@RequestMapping("grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;
    private final GradeMapper gradeMapper;
    private final CreateGradeAction gradeAction;

    @PostMapping("create")
    @Operation(description = "Создание оценки")
    public GradeDTO create(@RequestBody @Valid CreateGradeDTO dto){
        Grade grade = gradeAction.create(gradeMapper.toCreateActionArgument(dto));

        return gradeMapper.toDto(grade);
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление оценки по id")
    public void delete(@PathVariable("id") int id){
        gradeService.delete(id);
    }

    @GetMapping("{recordId}")
    @Operation(description = "Получение всех оценок записи по ее id")
    public Page<GradeDTO> findAllByRecordId(@PathVariable("recordId") int recordId,
                                            @RequestParam(value = "sort", defaultValue = "grade") String sortField,
                                            @RequestParam(value = "direction", defaultValue = "asc") String sortDirection,
                                            Pageable pageable){
        Page<Grade> resultPage = gradeService.findAllByRecordId(recordId, sortField, sortDirection, pageable);
        return resultPage.map(gradeMapper::toDto);
    }

    @GetMapping("grade")
    @Operation(description = "Получение всех оценок с заданным значением")
    public Page<GradeDTO> findAllGradesByGrade(@RequestParam("grade") int grade,
                                               @RequestParam(value = "sort", defaultValue = "grade") String sortField,
                                               @RequestParam(value = "direction", defaultValue = "asc") String sortDirection,
                                               Pageable pageable){
        Page<Grade> resultPage = gradeService.findAllGradesByGrade(grade, sortField, sortDirection, pageable);
        return resultPage.map(gradeMapper::toDto);
    }
}
