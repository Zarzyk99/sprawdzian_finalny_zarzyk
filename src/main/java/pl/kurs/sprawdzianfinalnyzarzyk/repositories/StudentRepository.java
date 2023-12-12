package pl.kurs.sprawdzianfinalnyzarzyk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
