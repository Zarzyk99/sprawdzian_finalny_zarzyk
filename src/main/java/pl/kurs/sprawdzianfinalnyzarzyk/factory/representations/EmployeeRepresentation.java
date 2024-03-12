package pl.kurs.sprawdzianfinalnyzarzyk.factory.representations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employment;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Position;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PositionRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRepresentation implements PersonRepresentation {
    private final PositionRepository positionRepository;

    @Override
    public String getType() {
        return "employee";
    }

    @Override
    public Person create(PeopleCsvRepresentation representation) {
        var employee = new Employee();
        employee.setFirstName(representation.getFirstName());
        employee.setLastName(representation.getLastName());
        employee.setPesel(representation.getPesel());
        employee.setGrowth(representation.getGrowth());
        employee.setWeight(representation.getWeight());
        employee.setEmail(representation.getEmail());
        LocalDate dateOfEmployment = LocalDate.parse(representation.getDateOfEmployment(), DateTimeFormatter.ISO_LOCAL_DATE);

        employee.setDateOfEmployment(dateOfEmployment);
        employee.setDtype(representation.getType());

        var position = new Position(representation.getCurrentPosition(), representation.getCurrentSalary());
        positionRepository.save(position);

        var employment = new Employment(position, dateOfEmployment);
        employee.setEmployments(List.of(employment));

        return employee;
    }
}
