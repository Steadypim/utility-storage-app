package dev.steadypim.thewhitehw.homework1.api.grade.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@FieldDefaults(level = PRIVATE)
public class SearchGradeResultDTO {
    List<GradeDTO> grades;
    long totalElements;
}
