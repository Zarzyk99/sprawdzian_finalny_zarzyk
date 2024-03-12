package pl.kurs.sprawdzianfinalnyzarzyk.factory.representations;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;

@Service
public class StudentRepresentation implements PersonRepresentation {
    @Override
    public String getType() {
        return "student";
    }

    @Override
    public Person create(PeopleCsvRepresentation representation) {
        var student = new Student();
        student.setFirstName(representation.getFirstName());
        student.setLastName(representation.getLastName());
        student.setPesel(representation.getPesel());
        student.setGrowth(representation.getGrowth());
        student.setWeight(representation.getWeight());
        student.setEmail(representation.getEmail());
        student.setNameOfUniversity(representation.getNameOfUniversity());
        student.setYearOfStudy(representation.getYearOfStudy());
        student.setFieldOfStudy(representation.getFieldOfStudy());
        student.setAmountOfScholarship(representation.getAmountOfScholarship());
        return student;
    }
}
