package pl.kurs.sprawdzianfinalnyzarzyk.services;

import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreateEmploymentCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employment;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Position;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.EmployeeRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.EmploymentRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PositionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmploymentRepository employmentRepository;
    private final PositionRepository positionRepository;


    public Employment assignPosition(Long id, CreateEmploymentCommand employmentCommand) {
        Employee employee = getEmployee(id);
        Position position = positionRepository.findById(employmentCommand.getPositionId())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));

        List<Employment> employments = employee.getEmployments();
        for (Employment employment : employments) {
            if (employmentCommand.getEndDate().isAfter(employment.getStartDate())
                    && employmentCommand.getStartDate().isBefore(employment.getEndDate())) {
                throw new InvalidRequestStateException("The dates overlap with an existing employment");
            }
        }
        Employment employment = new Employment();
        employment.setPosition(position);
        employment.setEmployee(employee);
        employment.setStartDate(employmentCommand.getStartDate());
        employment.setEndDate(employmentCommand.getEndDate());

        Employment savedEmployemnt = employmentRepository.save(employment);
        employee.getEmployments().add(employment);
        employee.setCurrentPosition(employment.getPosition().getPositionName());

        return savedEmployemnt;
    }

    public List<Employment> getPositions(Long id) {
        Employee employee = getEmployee(id);

        return employee.getEmployments();
    }

    public void removePosition(Long employeeId, Long employmentId) {
        Employee employee = getEmployee(employeeId);
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new EntityNotFoundException("Employment not found"));

        if (!employment.getEmployee().equals(employee)) {
            throw new InvalidRequestStateException("The employment does not belong to the employee");
        }
        employmentRepository.delete(employment);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));
    }

}
