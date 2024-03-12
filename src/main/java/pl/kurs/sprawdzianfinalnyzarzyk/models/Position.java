package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String positionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double salary;

    @OneToMany(mappedBy = "currentPosition")
    private List<Employee> employees;
}
