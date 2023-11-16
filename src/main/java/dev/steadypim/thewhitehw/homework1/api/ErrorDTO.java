package dev.steadypim.thewhitehw.homework1.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorDTO {
    private List<String> errorMessages;
}
