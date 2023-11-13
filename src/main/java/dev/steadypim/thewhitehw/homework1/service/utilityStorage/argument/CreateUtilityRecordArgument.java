package dev.steadypim.thewhitehw.homework1.service.utilityStorage.argument;

import dev.steadypim.thewhitehw.homework1.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUtilityRecordArgument {
    private String name;
    private String description;
    private String link;
    private Map<Integer, Grade> grades;
}
