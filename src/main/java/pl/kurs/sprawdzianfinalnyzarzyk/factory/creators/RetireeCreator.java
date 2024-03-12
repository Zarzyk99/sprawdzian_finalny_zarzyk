package pl.kurs.sprawdzianfinalnyzarzyk.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Retiree;

import java.util.Map;

@Service
public class RetireeCreator implements PersonCreator, Parametric {
    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public Person create(Map<String, Object> parameters) {
        return new Retiree(getStringParameter("firstName", parameters),
                getStringParameter("lastName", parameters),
                getStringParameter("pesel", parameters),
                getIntegerParameters("growth", parameters),
                getDoubleParameters("weight", parameters),
                getStringParameter("email", parameters),
                getDoubleParameters("amountOfPension", parameters),
                getIntegerParameters("yearsWorked", parameters));
    }
}
