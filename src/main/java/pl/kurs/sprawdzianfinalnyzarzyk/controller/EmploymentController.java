package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreateEmploymentCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.EmploymentDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.StatusDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employment;
import pl.kurs.sprawdzianfinalnyzarzyk.services.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmployeeService employeeService;
    private final ModelMapper mapper;

    @PostMapping("/{id}/positions")
    public ResponseEntity<EmploymentDto> assignPosition(@PathVariable Long id, @RequestBody CreateEmploymentCommand employmentCommand) {
        Employment employment = employeeService.assignPosition(id, employmentCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(toEmploymentDto(employment));
    }

    @GetMapping("/{id}/position")
    public ResponseEntity<List<EmploymentDto>> getPositions(@PathVariable Long id) {
        List<Employment> employments = employeeService.getPositions(id);
        List<EmploymentDto> dtos = employments.stream()
                .map(this::toEmploymentDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{employeeId}/positions/{employmentId}")
    public ResponseEntity<StatusDto> removePosition(@PathVariable Long employeeId, @PathVariable Long employmentId) {
        employeeService.removePosition(employeeId, employmentId);
        return ResponseEntity.ok(new StatusDto("Position from employee with id: " + employeeId + " deleted"));
    }

    public EmploymentDto toEmploymentDto(Employment employment) {
        return mapper.map(employment, EmploymentDto.class);
    }


}
