package dev.steadypim.thewhitehw.homework1.service.grade;

import com.querydsl.core.BooleanBuilder;
import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.QGrade;
import dev.steadypim.thewhitehw.homework1.repository.grade.GradeRepository;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.SearchGradeArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository repository;

    @Override
    @Transactional
    public Grade create(CreateGradeArgument argument) {
        Grade grade = Grade.builder()
                .utilityStorage(argument.getUtilityStorage())
                .comment(argument.getComment())
                .grade(argument.getGrade())
                .build();
        return repository.save(grade);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Grade> searchGrades(SearchGradeArgument argument) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (argument.getRecordId() != null) {
            predicate.and(QGrade.grade1.utilityStorage().id.eq(argument.getRecordId()));
        }

        if (argument.getGrade() != null) {
            predicate.and(QGrade.grade1.grade.eq(argument.getGrade()));
        }

        return repository.findAll(predicate, argument.getPageable());
    }
}
