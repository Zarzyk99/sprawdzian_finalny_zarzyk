package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kurs.sprawdzianfinalnyzarzyk.SprawdzianFinalnyZarzykApplication;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreateEmploymentCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.EmploymentDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Position;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.EmployeeRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PositionRepository;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SprawdzianFinalnyZarzykApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmploymentControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "employee", roles = "EMPLOYEE")
    public void shouldAssignPositionForEmployee() throws Exception {
        Employee employee = new Employee("Ernest", "Nowak",
                "87011245689", 175, 75.00, "ernesto@gmail.com");

        Position position = new Position("Tester", 6545.43);

        positionRepository.save(position);
        employeeRepository.save(employee);

        CreateEmploymentCommand command = new CreateEmploymentCommand();
        command.setPositionId(position.getId());
        command.setStartDate(LocalDate.now());
        command.setEndDate(LocalDate.now().plusDays(10));

        mockMvc.perform(post("/api/employment/" + employee.getId() + "/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.position", is(position.getPositionName())));

        mockMvc.perform(get("/api/employment/" + employee.getId() + "/position")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].position", is(position.getPositionName())));
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldDeletePosition() throws Exception {
        Employee employee = new Employee("Maciej", "Tyka",
                "93052545689", 177, 80.00, "maciejoo@gmail.com");

        Position position = new Position("Kierownik", 5545.43);

        positionRepository.save(position);
        employeeRepository.save(employee);

        CreateEmploymentCommand command = new CreateEmploymentCommand();
        command.setPositionId(position.getId());
        command.setStartDate(LocalDate.now());
        command.setEndDate(LocalDate.now().plusDays(10));

        ResultActions result = mockMvc.perform(post("/api/employment/" + employee.getId() + "/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(command)));

        String response = result.andReturn().getResponse().getContentAsString();
        EmploymentDto employmentDto = mapper.readValue(response, EmploymentDto.class);


        mockMvc.perform(delete("/api/employment/" + employee.getId() + "/positions/" + employmentDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Position from employee with id: " + employee.getId() + " deleted")));
    }

}