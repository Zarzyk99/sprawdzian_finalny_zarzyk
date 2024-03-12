package pl.kurs.sprawdzianfinalnyzarzyk.dto.full;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RetireeFullDto implements PersonFullDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private Integer growth;

    private Double weight;

    private String email;

    private Double amountOfPension;

    private Integer yearsWorked;

    @Override
    public String getType() {
        return "retiree";
    }
}
