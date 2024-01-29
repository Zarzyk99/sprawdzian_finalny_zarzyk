package pl.kurs.sprawdzianfinalnyzarzyk.dto.full;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeeFullDto implements PersonFullDto {
    private String firstName;

    private String lastName;

    private String pesel;

    private Integer growth;

    private Double weight;

    private String email;

    private LocalDate dateOfEmployment;

    private String currentPosition;

    private double currentSalary;

    private String dtype;

    @Override
    public String getType() {
        return "employee";
    }
}
