package pl.kurs.sprawdzianfinalnyzarzyk.dto.full;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeeFullDto implements PersonFullDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private Integer growth;

    private Double weight;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEmployment;

    private String currentPosition;

    private double currentSalary;

    @Override
    public String getType() {
        return "employee";
    }
}
