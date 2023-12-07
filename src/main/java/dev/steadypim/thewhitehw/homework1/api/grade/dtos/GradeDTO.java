package dev.steadypim.thewhitehw.homework1.api.grade.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private int id;
    private int grade;
    private String comment;
}
