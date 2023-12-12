package pl.kurs.sprawdzianfinalnyzarzyk.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CreatePersonCommand {
    private String personType;
    private Map<String, Object> parameters;
}
