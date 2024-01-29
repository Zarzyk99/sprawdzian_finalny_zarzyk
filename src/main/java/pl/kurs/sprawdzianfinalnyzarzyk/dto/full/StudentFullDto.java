package pl.kurs.sprawdzianfinalnyzarzyk.dto.full;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentFullDto implements PersonFullDto {
    private String firstName;

    private String lastName;

    private String pesel;

    private Integer growth;

    private Double weight;

    private String email;

    private String nameOfUniversity;

    private Integer yearOfStudy;

    private String fieldOfStudy;

    private Double amountOfScholarship;

    private String dtype;

    @Override
    public String getType() {
        return "student";
    }
}
