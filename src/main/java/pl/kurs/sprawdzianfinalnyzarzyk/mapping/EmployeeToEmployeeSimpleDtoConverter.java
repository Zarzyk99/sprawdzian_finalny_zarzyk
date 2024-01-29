package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.EmployeeSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;

@Service
public class EmployeeToEmployeeSimpleDtoConverter implements PersonToPersonSimpleDtoConverter<Employee, EmployeeSimpleDto> {
    @Override
    public EmployeeSimpleDto convert(MappingContext<Employee, EmployeeSimpleDto> context) {
        Employee source = context.getSource();
        return EmployeeSimpleDto.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .currentPosition(source.getCurrentPosition())
                .dtype(source.getDtype())
                .build();
    }

    @Override
    public EmployeeSimpleDto getDtoType() {
        return EmployeeSimpleDto.builder().build();
    }

    @Override
    public Class<Employee> getBaseEntityClass() {
        return Employee.class;
    }
}
