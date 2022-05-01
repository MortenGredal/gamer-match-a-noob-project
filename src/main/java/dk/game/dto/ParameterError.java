package dk.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterError {

    private String parameter;
    private String message;

}
