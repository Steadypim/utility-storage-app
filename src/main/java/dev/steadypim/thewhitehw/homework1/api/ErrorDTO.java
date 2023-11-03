package dev.steadypim.thewhitehw.homework1.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorDTO {
    private String errorMessage;
}
