package pl.kurs.sprawdzianfinalnyzarzyk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
