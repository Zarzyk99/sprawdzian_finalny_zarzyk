package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.StudentSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Student;

@Service
public class StudentToStudentSimpleDtoConverter implements PersonToPersonSimpleDtoConverter<Student, StudentSimpleDto> {
    @Override
    public StudentSimpleDto convert(MappingContext<Student, StudentSimpleDto> context) {
        Student source = context.getSource();
        return StudentSimpleDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .build();
    }

    @Override
    public StudentSimpleDto getDtoType() {
        return StudentSimpleDto.builder().build();
    }

    @Override
    public Class<Student> getBaseEntityClass() {
        return Student.class;
    }
}
