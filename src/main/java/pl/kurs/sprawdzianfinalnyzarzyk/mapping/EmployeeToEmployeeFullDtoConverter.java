package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.EmployeeFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;

@Service
public class EmployeeToEmployeeFullDtoConverter implements PersonToPersonFullDtoConverter<Employee, EmployeeFullDto> {

    @Override
    public EmployeeFullDto convert(MappingContext<Employee, EmployeeFullDto> context) {
        Employee source = context.getSource();
        return EmployeeFullDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .pesel(source.getPesel())
                .growth(source.getGrowth())
                .weight(source.getWeight())
                .email(source.getEmail())
                .dateOfEmployment(source.getDateOfEmployment())
                .currentPosition(source.getCurrentPosition())
                .currentSalary(source.getCurrentSalary())
                .build();
    }

    private String getPosition(Employee source) {
        LocalDate today = LocalDate.now();

        return source.getEmployments().stream()
                .filter(employment -> employment.getStartDate().isBefore(today) && (employment.getEndDate() == null ||
                        employment.getEndDate().isAfter(today)))
                .findFirst()
                .map(employment -> employment.getPosition().getPositionName())
                .orElse(null);
    }

    @Override
    public EmployeeFullDto getDtoType() {
        return EmployeeFullDto.builder().build();
    }

    @Override
    public Class<Employee> getBaseEntityClass() {
        return Employee.class;
    }
}
