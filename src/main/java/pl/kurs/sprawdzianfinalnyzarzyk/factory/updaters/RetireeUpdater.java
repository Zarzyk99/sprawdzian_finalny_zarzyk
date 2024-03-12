package pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Retiree;

import java.util.Map;

@Service
public class RetireeUpdater implements PersonUpdater, Parametric {
    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public Person update(Person person, Map<String, Object> parameters) {
        if (person instanceof Retiree) {
            Retiree retiree = (Retiree) person;
            retiree.setFirstName(getStringParameter("firstName", parameters));
            retiree.setLastName(getStringParameter("lastName", parameters));
            retiree.setPesel(getStringParameter("pesel", parameters));
            retiree.setGrowth(getIntegerParameters("growth", parameters));
            retiree.setWeight(getDoubleParameters("weight", parameters));
            retiree.setEmail(getStringParameter("email", parameters));
            retiree.setAmountOfPension(getDoubleParameters("amountOfPension", parameters));
            retiree.setYearsWorked(getIntegerParameters("yearsWorked", parameters));
            return retiree;
        } else {
            throw new IllegalArgumentException("Invalid person type for RetireeUpdater");
        }
    }
}
