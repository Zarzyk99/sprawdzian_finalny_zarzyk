package pl.kurs.sprawdzianfinalnyzarzyk.dto.simple;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentSimpleDto implements PersonSimpleDto {

    private String firstName;

    private String lastName;

    private String email;

    private String nameOfUniversity;

    private String fieldOfStudy;

    private String dtype;

    @Override
    public String getType() {
        return "student";
    }
}
