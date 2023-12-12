package pl.kurs.sprawdzianfinalnyzarzyk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
