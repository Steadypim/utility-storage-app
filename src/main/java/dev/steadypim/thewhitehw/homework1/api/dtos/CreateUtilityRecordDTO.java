package dev.steadypim.thewhitehw.homework1.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUtilityRecordDTO {
    private String name;
    private String description;
    private String link;
}
