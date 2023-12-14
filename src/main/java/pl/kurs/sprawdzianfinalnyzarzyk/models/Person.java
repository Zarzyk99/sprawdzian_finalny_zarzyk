package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = SINGLE_TABLE)
public abstract class Person implements Serializable, Identifiable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String pesel;

    @Column(nullable = false)
    private Integer growth;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String email;

    @Column(insertable = false, updatable = false)
    private String dtype;

    public Person(String firstName, String lastName, String pesel, Integer growth, Double weight, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.growth = growth;
        this.weight = weight;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName) && Objects.equals(pesel, person.pesel)
                && Objects.equals(growth, person.growth) && Objects.equals(weight, person.weight)
                && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel, growth, weight, email);
    }
}
