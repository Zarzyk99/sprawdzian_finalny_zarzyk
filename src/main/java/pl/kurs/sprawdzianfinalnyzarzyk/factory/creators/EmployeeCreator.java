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
//
//    @Override
//    public Person createPersonFromCsv(String[] line) {
//        return new Employee(line[1], line[2], line[3], Integer.parseInt(line[4]), Double.parseDouble(line[5]),
//                line[6], LocalDate.parse(line[11], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                line[12], Double.parseDouble(line[13]));
//
//
//    }
}
