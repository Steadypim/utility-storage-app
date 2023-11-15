package dev.steadypim.thewhitehw.homework1.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Оценка
 */
@Data
@Builder
public class Grade {
    private int id;
    private int recordId;
    private int grade;
    private String comment;
}


