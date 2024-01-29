package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.PersonFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonDtoFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Position;
import pl.kurs.sprawdzianfinalnyzarzyk.services.EmployeeService;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper mapper;
    private final PersonDtoFactory personDtoFactory;


//    @PutMapping("/{id}/position")
//    public ResponseEntity<?> updatePosition(@PathVariable Long id, @RequestBody Position newPosition){
//        try {
//            Employee employee = employeeService.getEmployee(id);
//            Position currentPosition = employee.getCurrentPosition();
//
//            if (currentPosition.getEndDate().isAfter(newPosition.getStartDate())){
//                return ResponseEntity.badRequest().body("New position dates overlap with current position dates.");
//            }
//            employee.setCurrentPosition(newPosition);
//
//            return ResponseEntity.ok(toFullDto(employeeService.saveEmployee(employee)));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("An error occurred while updating the position.");
//        }
//    }

    public PersonFullDto toFullDto(Person person) {
        return mapper.map(person, personDtoFactory.findFullDtoConverter(person).getDtoType().getClass());
    }
}
