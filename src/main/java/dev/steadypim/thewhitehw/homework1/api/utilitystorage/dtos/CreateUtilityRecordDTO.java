package dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUtilityRecordDTO {
    @NotBlank(message = "Имя не указано")
    private String name;
    private String description;
    private String link;
}
