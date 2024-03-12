package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position currentPosition;

    private double currentSalary;

    public Employee(String firstName, String lastName, String pesel, Integer growth, Double weight, String email) {
        super(firstName, lastName, pesel, growth, weight, email);
    }

    public Employee(String firstName, String lastName, String pesel, Integer growth, Double weight,
                    String email, List<Employment> employments, String currentPosition, double currentSalary) {
        super(firstName, lastName, pesel, growth, weight, email);
        this.employments = employments;
        this.currentPosition = currentPosition;
        this.currentSalary = currentSalary;
    }

    @Override
    public Employee setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    @Override
    public Employee setLastName(String lastName) {
        super.setLastName(lastName);
        return this;
    }

    @Override
    public Employee setPesel(String pesel) {
        super.setPesel(pesel);
        return this;
    }

    @Override
    public Employee setGrowth(Integer growth) {
        super.setGrowth(growth);
        return this;
    }

    @Override
    public Employee setWeight(Double weight) {
        super.setWeight(weight);
        return this;
    }

    @Override
    public Employee setEmail(String email) {
        super.setEmail(email);
        return this;
    }

    @Override
    public Employee setDtype(String dtype) {
        super.setDtype(dtype);
        return this;
    }

    public Employee setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
        return this;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Employee setEmployments(List<Employment> employments) {
        this.employments = employments;
        return this;
    }

    public Employee setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
        return this;
    }
}
