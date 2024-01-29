package pl.kurs.sprawdzianfinalnyzarzyk.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }
}
