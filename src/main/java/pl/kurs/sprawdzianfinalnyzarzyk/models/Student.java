package pl.kurs.sprawdzianfinalnyzarzyk.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student extends Person {
    private String nameOfUniversity;

    private Integer yearOfStudy;

    private String fieldOfStudy;

    private Double amountOfScholarship;

    public Student(String firstName, String lastName, String pesel, Integer growth, Double weight, String email,
                   String nameOfUniversity, Integer yearOfStudy, String fieldOfStudy, Double amountOfScholarship) {
        super(firstName, lastName, pesel, growth, weight, email);
        this.nameOfUniversity = nameOfUniversity;
        this.yearOfStudy = yearOfStudy;
        this.fieldOfStudy = fieldOfStudy;
        this.amountOfScholarship = amountOfScholarship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(nameOfUniversity, student.nameOfUniversity)
                && Objects.equals(yearOfStudy, student.yearOfStudy)
                && Objects.equals(fieldOfStudy, student.fieldOfStudy)
                && Objects.equals(amountOfScholarship, student.amountOfScholarship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfUniversity, yearOfStudy, fieldOfStudy, amountOfScholarship);
    }
}
