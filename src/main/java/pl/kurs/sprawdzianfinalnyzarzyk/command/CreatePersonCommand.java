package pl.kurs.sprawdzianfinalnyzarzyk.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.sprawdzianfinalnyzarzyk.validations.ValidParameters;

import java.util.Map;

@Getter
@Setter
public class CreatePersonCommand {
    @NotBlank(message = "Person type can not be blank")
    private String personType;

    @ValidParameters
    private Map<String, Object> parameters;
}
