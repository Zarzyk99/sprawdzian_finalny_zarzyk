package pl.kurs.sprawdzianfinalnyzarzyk.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.Parametric;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;

import java.util.Map;

@Service
public class StudentCreator implements PersonCreator, Parametric {
    @Override
    public String getType() {
        return "student";
    }

    @Override
    public Person create(Map<String, Object> parameters) {
        return new Student(getStringParameter("firstName", parameters),
                getStringParameter("lastName", parameters),
                getStringParameter("pesel", parameters),
                getIntegerParameters("growth", parameters),
                getDoubleParameters("weight", parameters),
                getStringParameter("email", parameters),
                getStringParameter("nameOfUniversity", parameters),
                getIntegerParameters("yearOfStudy", parameters),
                getStringParameter("fieldOfStudy", parameters),
                getDoubleParameters("amountOfScholarship", parameters));
    }
}
