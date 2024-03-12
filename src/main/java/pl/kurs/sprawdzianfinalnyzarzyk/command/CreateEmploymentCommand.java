package pl.kurs.sprawdzianfinalnyzarzyk.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateEmploymentCommand {
    private Long positionId;
    private LocalDate startDate;
    private LocalDate endDate;
}
