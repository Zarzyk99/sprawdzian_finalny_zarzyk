package pl.kurs.sprawdzianfinalnyzarzyk.dto.simple;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RetireeSimpleDto implements PersonSimpleDto{
    private String firstName;

    private String lastName;

    private String email;

    private Integer yearsWorked;

    private String dtype;
    @Override
    public String getType() {
        return "retiree";
    }
}
