package dev.steadypim.thewhitehw.homework1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Оценка
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private int id;
    private int recordId;
    private int grade;
    private String comment;
}


