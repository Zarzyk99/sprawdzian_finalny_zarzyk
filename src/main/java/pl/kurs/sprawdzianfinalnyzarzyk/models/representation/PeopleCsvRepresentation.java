package pl.kurs.sprawdzianfinalnyzarzyk.models.representation;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleCsvRepresentation {

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "firstName")
    private String firstName;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "pesel")
    private String pesel;

    @CsvBindByName(column = "growth")
    private Integer growth;

    @CsvBindByName(column = "weight")
    private Double weight;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "nameOfUniversity")
    private String nameOfUniversity;

    @CsvBindByName(column = "yearOfStudy")
    private Integer yearOfStudy;

    @CsvBindByName(column = "fieldOfStudy")
    private String fieldOfStudy;

    @CsvBindByName(column = "amountOfScholarship")
    private Double amountOfScholarship;

    @CsvBindByName(column = "dateOfEmployment")
    private String dateOfEmployment;

    @CsvBindByName(column = "currentPosition")
    private String currentPosition;

    @CsvBindByName(column = "currentSalary")
    private Double currentSalary;

    @CsvBindByName(column = "amountOfPension")
    private Double amountOfPension;

    @CsvBindByName(column = "yearsWorked")
    private Integer yearsWorked;
}
