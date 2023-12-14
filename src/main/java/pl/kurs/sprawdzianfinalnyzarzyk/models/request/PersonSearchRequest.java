package pl.kurs.sprawdzianfinalnyzarzyk.models.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonSearchRequest {

    private String dtype;

    private String firstName;

    private String lastName;

    private String pesel;

    private Integer minGrowth;

    private Integer maxGrowth;

    private Double minWeight;

    private Double maxWeight;

    private String email;

    private LocalDate startRangeOfEmploymentDate;

    private LocalDate endRangeOfEmploymentDate;

    private String currentPosition;

    private Double minSalary;

    private Double maxSalary;

    private String nameOfUniversity;

    private Integer minYearOfStudy;

    private Integer maxYearOfStudy;

    private String fieldOfStudy;

    private Double minScholarship;

    private Double maxScholarship;

    private Double minPension;

    private Double maxPension;

    private Integer minYearsWorked;

    private Integer maxYearsWorked;
}
