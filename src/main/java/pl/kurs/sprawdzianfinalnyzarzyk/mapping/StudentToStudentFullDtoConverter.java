package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.StudentFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;

@Service
public class StudentToStudentFullDtoConverter implements PersonToPersonFullDtoConverter<Student, StudentFullDto> {

    @Override
    public StudentFullDto convert(MappingContext<Student, StudentFullDto> context) {
        Student source = context.getSource();
        return StudentFullDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .pesel(source.getPesel())
                .growth(source.getGrowth())
                .weight(source.getWeight())
                .email(source.getEmail())
                .nameOfUniversity(source.getNameOfUniversity())
                .yearOfStudy(source.getYearOfStudy())
                .fieldOfStudy(source.getFieldOfStudy())
                .amountOfScholarship(source.getAmountOfScholarship())
                .build();
    }

    @Override
    public StudentFullDto getDtoType() {
        return StudentFullDto.builder().build();
    }

    @Override
    public Class<Student> getBaseEntityClass() {
        return Student.class;
    }
}
