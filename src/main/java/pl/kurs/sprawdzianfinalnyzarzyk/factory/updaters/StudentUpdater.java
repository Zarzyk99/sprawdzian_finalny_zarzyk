package pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;

import java.util.Map;

@Service
public class StudentUpdater implements PersonUpdater, Parametric {
    @Override
    public String getType() {
        return "student";
    }

    @Override
    public Person update(Person person, Map<String, Object> parameters) {
        if (person instanceof Student){
            Student student = (Student) person;
            student.setFirstName(getStringParameter("firstName", parameters));
            student.setLastName(getStringParameter("lastName", parameters));
            student.setPesel(getStringParameter("pesel", parameters));
            student.setGrowth(getIntegerParameters("growth", parameters));
            student.setWeight(getDoubleParameters("weight", parameters));
            student.setEmail(getStringParameter("email", parameters));
            student.setNameOfUniversity(getStringParameter("nameOfUniversity", parameters));
            student.setYearOfStudy(getIntegerParameters("yearOfStudy", parameters));
            student.setFieldOfStudy(getStringParameter("fieldOfStudy", parameters));
            student.setAmountOfScholarship(getDoubleParameters("amountOfScholarship", parameters));
            return student;
        } else {
            throw new IllegalArgumentException("Invalid person type for EmployeeUpdater");

        }
    }
}
