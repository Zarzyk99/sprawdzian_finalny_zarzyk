package pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

@Service
public class EmployeeUpdater implements PersonUpdater, Parametric {

    @Override
    public String getType() {
        return "employee";
    }

    @Override
    public Person update(Person person, Map<String, Object> parameters) {
        if (person instanceof Employee) {
            Employee employee = (Employee) person;
            employee.setFirstName(getStringParameter("firstName", parameters));
            employee.setLastName(getStringParameter("lastName", parameters));
            employee.setPesel(getStringParameter("pesel", parameters));
            employee.setGrowth(getIntegerParameters("growth", parameters));
            employee.setWeight(getDoubleParameters("weight", parameters));
            employee.setEmail(getStringParameter("email", parameters));
            employee.setDateOfEmployment(getLocalDateParameters("dateOfEmployment", parameters));
            employee.setCurrentPosition(getStringParameter("currentPosition", parameters));
            employee.setCurrentSalary(getDoubleParameters("currentSalary", parameters));
            return employee;
        } else {
            throw new IllegalArgumentException("Invalid person type for EmployeeUpdater");
        }
    }
}
