package pl.kurs.sprawdzianfinalnyzarzyk.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmploymentDto {
    private Long id;

    private String position;

    private LocalDate startDate;

    private LocalDate endDate;
}
