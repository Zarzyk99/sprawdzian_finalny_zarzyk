package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Person {
    private LocalDate dateOfEmployment;

    private String currentPosition;

    private double currentSalary;

    public Employee(String firstName, String lastName, String pesel, Integer growth, Double weight, String email,
                    LocalDate dateOfEmployment, String currentPosition, double currentSalary) {
        super(firstName, lastName, pesel, growth, weight, email);
        this.dateOfEmployment = dateOfEmployment;
        this.currentPosition = currentPosition;
        this.currentSalary = currentSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.currentSalary, currentSalary) == 0 && Objects.equals(dateOfEmployment, employee.dateOfEmployment) && Objects.equals(currentPosition, employee.currentPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateOfEmployment, currentPosition, currentSalary);
    }
}
