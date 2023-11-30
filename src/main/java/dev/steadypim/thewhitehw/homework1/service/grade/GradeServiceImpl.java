package dev.steadypim.thewhitehw.homework1.service.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import dev.steadypim.thewhitehw.homework1.entity.UtilityStorage;
import dev.steadypim.thewhitehw.homework1.repository.grade.GradeRepository;
import dev.steadypim.thewhitehw.homework1.service.grade.argument.CreateGradeArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService{

    private final GradeRepository repository;
    @Override
    public Grade create(CreateGradeArgument argument, UtilityStorage record) {
        Grade grade = Grade.builder()
                .utilityStorage(record)
                .comment(argument.getComment())
                .grade(argument.getGrade())
                .build();
        return repository.save(grade);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Grade> findAllByRecordId(int utilityStorageId, String sortField, String sortDirection, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return repository.findAllByUtilityStorageId(utilityStorageId, pageable);
    }

    @Override
    public Page<Grade> findAllGradesByGrade(int grade, String sortField, String sortDirection, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return repository.findAllByGrade(grade, pageable);
    }
}
