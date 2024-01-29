package pl.kurs.sprawdzianfinalnyzarzyk.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UpdatePersonCommand {
    private String personType;
    private Map<String, Object> parameters;
}
