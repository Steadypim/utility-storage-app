package dev.steadypim.thewhitehw.homework1.service.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.exception.EntityNotFoundException;
import dev.steadypim.thewhitehw.homework1.repository.grade.GradeRepository;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService{

    private final GradeRepository repository;
    @Override
    public Grade create(CreateGradeArgument argument) {
        Grade grade = Grade.builder()
                .comment(argument.getComment())
                .recordId(argument.getRecordId())
                .grade(argument.getGrade())
                .build();
        return repository.create(grade);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Grade> findAllById(int recordId) {
        return Optional.ofNullable(repository.findAllById(recordId))
                .orElseThrow(() -> new EntityNotFoundException("Данная запись не имеет оценок"));
    }
}
