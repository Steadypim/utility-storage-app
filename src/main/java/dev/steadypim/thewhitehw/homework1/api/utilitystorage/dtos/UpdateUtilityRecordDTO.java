package dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUtilityRecordDTO {
    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    private String description;
    private List<String> links;
}
