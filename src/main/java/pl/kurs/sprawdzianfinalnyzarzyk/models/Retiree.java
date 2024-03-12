package pl.kurs.sprawdzianfinalnyzarzyk.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Retiree extends Person {
    private Double amountOfPension;

    private Integer yearsWorked;

    public Retiree(String firstName, String lastName, String pesel, Integer growth,
                   Double weight, String email, Double amountOfPension, Integer yearsWorked) {
        super(firstName, lastName, pesel, growth, weight, email);
        this.amountOfPension = amountOfPension;
        this.yearsWorked = yearsWorked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Retiree retiree = (Retiree) o;
        return Objects.equals(amountOfPension, retiree.amountOfPension) &&
                Objects.equals(yearsWorked, retiree.yearsWorked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amountOfPension, yearsWorked);
    }
}
