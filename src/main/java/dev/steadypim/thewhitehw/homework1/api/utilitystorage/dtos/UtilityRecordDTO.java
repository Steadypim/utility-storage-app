package dev.steadypim.thewhitehw.homework1.api.utilitystorage.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilityRecordDTO {
    private int id;
    private String name;
    private String description;
    private List<String> links;
}
