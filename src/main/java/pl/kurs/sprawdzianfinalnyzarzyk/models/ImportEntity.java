package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class ImportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ImportStatus status;

    private LocalDateTime startDate;

    private long processedRows;

    public ImportEntity() {
        this.status = ImportStatus.NEW;
        startDate = LocalDateTime.now();
    }
}
