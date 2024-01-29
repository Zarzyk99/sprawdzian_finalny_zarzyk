package pl.kurs.sprawdzianfinalnyzarzyk.dto.simple;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeeSimpleDto implements PersonSimpleDto{
    private String firstName;

    private String lastName;

    private String email;

    private String currentPosition;

    private String dtype;

    @Override
    public String getType() {
        return "employee";
    }
}
