package dev.steadypim.thewhitehw.homework1.service.argument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUtilityRecordArgument {
    private String name;
    private String description;
    private String link;
}
