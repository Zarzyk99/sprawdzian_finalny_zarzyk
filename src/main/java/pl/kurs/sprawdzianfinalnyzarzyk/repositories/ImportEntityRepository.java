package pl.kurs.sprawdzianfinalnyzarzyk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.models.ImportEntity;

public interface ImportEntityRepository extends JpaRepository<ImportEntity, Integer> {
}
