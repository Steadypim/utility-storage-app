package dev.steadypim.thewhitehw.homework1.api.grade.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGradeDTO {
    @NotNull(message = "id записи не указан")
    private int recordId;

    @NotNull(message = "оценка не указана")
    @Min(value = 1, message = "оценка не может быть меньше 1")
    @Max(value = 5, message = "Оценка не может быть больше 5")
    private int grade;

    @NotBlank(message = "комментарий не указан")
    private String comment;
}
