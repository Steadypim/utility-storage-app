package dev.steadypim.thewhitehw.homework1.repository.grade;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GradeRepositoryImpl implements GradeRepository{

    private final Map<Integer, Grade> grades;
    private AtomicInteger idCounter;

    @PostConstruct
    public void initializeCounter(){
        idCounter = new AtomicInteger(1);
    }

    @Override
    public Grade create(Grade grade) {
        int id = idCounter.getAndIncrement();
        grades.put(id, grade);
        return grades.get(grade.getId());
    }

    @Override
    public List<Grade> findAllById(int recordId) {
        return grades.values().stream()
                .filter(grade -> grade.getId() == recordId)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        grades.remove(id);
    }
}
