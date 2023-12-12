package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

@Service
public class EmployeeCreator implements PersonCreator {
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
                getLocalDateParameters("dateOfCommencementOfEmployment", parameters),//tutaj jest problem
                getStringParameter("currentPosition", parameters),
                getDoubleParameters("currentSalary", parameters));
    }
}
