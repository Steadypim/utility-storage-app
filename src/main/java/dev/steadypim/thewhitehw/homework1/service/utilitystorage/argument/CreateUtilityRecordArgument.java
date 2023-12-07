package dev.steadypim.thewhitehw.homework1.service.utilitystorage.argument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUtilityRecordArgument {
    private String name;
    private String description;
    private List<String> links;
}
