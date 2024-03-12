package pl.kurs.sprawdzianfinalnyzarzyk.dto.simple;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeSimpleDto implements PersonSimpleDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Override
    public String getType() {
        return "employee";
    }
}
