package pl.kurs.sprawdzianfinalnyzarzyk.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

@Service
public class EmployeeCreator implements PersonCreator, Parametric {
    @Override
    public String getType() {
        return "employee";
    }

    @Override
    public Person create(Map<String, Object> parameters) {
        return new Employee(getStringParameter("firstName", parameters),
                getStringParameter("lastName", parameters),
                getStringParameter("pesel", parameters),
                getIntegerParameters("growth", parameters),
                getDoubleParameters("weight", parameters),
                getStringParameter("email", parameters),
                getLocalDateParameters("dateOfEmployment", parameters),
                getStringParameter("currentPosition", parameters),
                getDoubleParameters("currentSalary", parameters));
    }

}
