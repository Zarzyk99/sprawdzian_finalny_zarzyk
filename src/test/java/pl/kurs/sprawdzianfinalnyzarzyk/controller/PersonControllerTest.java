package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kurs.sprawdzianfinalnyzarzyk.SprawdzianFinalnyZarzykApplication;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.ImportStatusDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.EmployeeFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.RetireeFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.StudentSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.*;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SprawdzianFinalnyZarzykApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PersonRepository personRepository;

    @BeforeAll
    void beforeAll() throws Exception {
        Map<String, Object> kacperData = new HashMap<>();
        kacperData.put("nameOfUniversity", "Politechnika Krakowska");
        kacperData.put("yearOfStudy", 3);
        kacperData.put("fieldOfStudy", "Energetyka");
        kacperData.put("amountOfScholarship", 324.00);
        kacperData.put("firstName", "Kacper");
        kacperData.put("lastName", "Kowalski");
        kacperData.put("pesel", "97022802398");
        kacperData.put("growth", 178);
        kacperData.put("weight", 81.00);
        kacperData.put("email", "kacperK@gmail.com");
        CreatePersonCommand kacper = new CreatePersonCommand();
        kacper.setPersonType("student");
        kacper.setParameters(kacperData);

        Map<String, Object> staszekData = new HashMap<>();
        staszekData.put("yearsWorked", 38);
        staszekData.put("amountOfPension", 1324.00);
        staszekData.put("firstName", "Stanisław");
        staszekData.put("lastName", "Kowalski");
        staszekData.put("pesel", "67101002398");
        staszekData.put("growth", 177);
        staszekData.put("weight", 79.00);
        staszekData.put("email", "stasiu@gmail.com");
        CreatePersonCommand staszek = new CreatePersonCommand();
        staszek.setPersonType("retiree");
        staszek.setParameters(staszekData);

        Map<String, Object> adamData = new HashMap<>();
        adamData.put("dateOfEmployment", "2023-12-12");
        adamData.put("currentPosition", "Serwisant");
        adamData.put("currentSalary", 4245.00);
        adamData.put("firstName", "Adam");
        adamData.put("lastName", "Nowak");
        adamData.put("pesel", "83081202398");
        adamData.put("growth", 175);
        adamData.put("weight", 75.00);
        adamData.put("email", "adam@gmail.com");
        CreatePersonCommand adam = new CreatePersonCommand();
        adam.setPersonType("employee");
        adam.setParameters(adamData);

        mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(kacper))));
        mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(staszek))));
        mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(adam))));
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldAddNewPersonOfStudentType() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nameOfUniversity", "Politechnika Rzeszowska");
        parameters.put("yearOfStudy", 1);
        parameters.put("fieldOfStudy", "Informatyka");
        parameters.put("amountOfScholarship", 234.00);
        parameters.put("firstName", "Bartek");
        parameters.put("lastName", "Nowak");
        parameters.put("pesel", "99051702398");
        parameters.put("growth", 173);
        parameters.put("weight", 84.00);
        parameters.put("email", "bartekN1@gmail.com");

        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonType("student");
        command.setParameters(parameters);

        ResultActions result = mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(command))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Bartek")))
                .andExpect(jsonPath("$.lastName", is("Nowak")))
                .andExpect(jsonPath("$.pesel", is("99051702398")))
                .andExpect(jsonPath("$.growth", is(173)))
                .andExpect(jsonPath("$.weight", is(84.00)))
                .andExpect(jsonPath("$.email", is("bartekN1@gmail.com")))
                .andExpect(jsonPath("$.nameOfUniversity", is("Politechnika Rzeszowska")))
                .andExpect(jsonPath("$.yearOfStudy", is(1)))
                .andExpect(jsonPath("$.fieldOfStudy", is("Informatyka")))
                .andExpect(jsonPath("$.amountOfScholarship", is(234.00)));

        String response = result.andReturn().getResponse().getContentAsString();
        Student student = mapper.readValue(response, Student.class);

        ResultActions expect = mockMvc.perform(get("/api/people/{id}", student.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String expectedResponse = expect.andReturn().getResponse().getContentAsString();
        Student expectedStudent = mapper.readValue(expectedResponse, Student.class);

        assertEquals("Bartek", expectedStudent.getFirstName());
        assertEquals("Nowak", expectedStudent.getLastName());
        assertEquals("99051702398", expectedStudent.getPesel());
        assertEquals(173, expectedStudent.getGrowth());
        assertEquals(84.00, expectedStudent.getWeight());
        assertEquals("bartekN1@gmail.com", expectedStudent.getEmail());
        assertEquals("Politechnika Rzeszowska", expectedStudent.getNameOfUniversity());
        assertEquals(1, expectedStudent.getYearOfStudy());
        assertEquals("Informatyka", expectedStudent.getFieldOfStudy());
        assertEquals(234.00, expectedStudent.getAmountOfScholarship());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldAddNewPersonOfRetireeType() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amountOfPension", 1460.00);
        parameters.put("yearsWorked", 41);
        parameters.put("firstName", "Ryszard");
        parameters.put("lastName", "Tkaczyk");
        parameters.put("pesel", "65041702398");
        parameters.put("growth", 168);
        parameters.put("weight", 73.00);
        parameters.put("email", "ryszardT@gmail.com");

        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonType("retiree");
        command.setParameters(parameters);

        ResultActions result = mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(command))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Ryszard")))
                .andExpect(jsonPath("$.lastName", is("Tkaczyk")))
                .andExpect(jsonPath("$.pesel", is("65041702398")))
                .andExpect(jsonPath("$.growth", is(168)))
                .andExpect(jsonPath("$.weight", is(73.00)))
                .andExpect(jsonPath("$.email", is("ryszardT@gmail.com")))
                .andExpect(jsonPath("$.yearsWorked", is(41)))
                .andExpect(jsonPath("$.amountOfPension", is(1460.00)));

        String response = result.andReturn().getResponse().getContentAsString();
        RetireeFullDto retireeFullDto = mapper.readValue(response, RetireeFullDto.class);

        ResultActions expect = mockMvc.perform(get("/api/people/{id}", retireeFullDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String expectedResponse = expect.andReturn().getResponse().getContentAsString();
        Retiree expectedRetiree = mapper.readValue(expectedResponse, Retiree.class);

        assertEquals("Ryszard", expectedRetiree.getFirstName());
        assertEquals("Tkaczyk", expectedRetiree.getLastName());
        assertEquals("65041702398", expectedRetiree.getPesel());
        assertEquals(168, expectedRetiree.getGrowth());
        assertEquals(73.00, expectedRetiree.getWeight());
        assertEquals("ryszardT@gmail.com", expectedRetiree.getEmail());
        assertEquals(41, expectedRetiree.getYearsWorked());
        assertEquals(1460.00, expectedRetiree.getAmountOfPension());

    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldAddNewPersonOfEmployeeType() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dateOfEmployment", "2020-12-12");
        parameters.put("currentPosition", "Ochroniarz");
        parameters.put("currentSalary", 3245.00);
        parameters.put("firstName", "Mariusz");
        parameters.put("lastName", "Mak");
        parameters.put("pesel", "79041702398");
        parameters.put("growth", 175);
        parameters.put("weight", 75.00);
        parameters.put("email", "mariuszM@gmail.com");

        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonType("employee");
        command.setParameters(parameters);

        ResultActions result = mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(command))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Mariusz")))
                .andExpect(jsonPath("$.lastName", is("Mak")))
                .andExpect(jsonPath("$.pesel", is("79041702398")))
                .andExpect(jsonPath("$.growth", is(175)))
                .andExpect(jsonPath("$.weight", is(75.00)))
                .andExpect(jsonPath("$.email", is("mariuszM@gmail.com")))
                .andExpect(jsonPath("$.dateOfEmployment", is("2020-12-12")))
                .andExpect(jsonPath("$.currentSalary", is(3245.00)))
                .andExpect(jsonPath("$.currentPosition", is("Ochroniarz")));

        String response = result.andReturn().getResponse().getContentAsString();
        EmployeeFullDto employeeFullDto = mapper.readValue(response, EmployeeFullDto.class);

        Optional<Person> expectedPerson = personRepository.findById(employeeFullDto.getId());
        assertTrue(expectedPerson.isPresent());


        if (expectedPerson.get() instanceof Employee) {
            Employee expectedEmployee = (Employee) expectedPerson.get();
            assertEquals("Mariusz", expectedEmployee.getFirstName());
            assertEquals("Mak", expectedEmployee.getLastName());
            assertEquals("79041702398", expectedEmployee.getPesel());
            assertEquals(175, expectedEmployee.getGrowth());
            assertEquals(75.00, expectedEmployee.getWeight());
            assertEquals("mariuszM@gmail.com", expectedEmployee.getEmail());
            assertEquals("2020-12-12", expectedEmployee.getDateOfEmployment().toString());
            assertEquals(3245.00, expectedEmployee.getCurrentSalary());
            assertEquals("Ochroniarz", expectedEmployee.getCurrentPosition());
        } else {
            fail("Person is not a Employee");
        }

    }


    @Test
    @WithMockUser(username = "employee", roles = "EMPLOYEE")
    public void shouldReturnAllPeopleOfPageOfPage() throws Exception {
        mockMvc.perform(get("/api/people")
                .param("page", "1")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldUpdatePerson() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nameOfUniversity", "Politechnika Rzeszowska");
        parameters.put("yearOfStudy", 1);
        parameters.put("fieldOfStudy", "Informatyka");
        parameters.put("amountOfScholarship", 234.00);
        parameters.put("firstName", "Bartek");
        parameters.put("lastName", "Nowak");
        parameters.put("pesel", "99041702398");
        parameters.put("growth", 173);
        parameters.put("weight", 84.00);
        parameters.put("email", "bartekN2@gmail.com");

        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonType("student");
        command.setParameters(parameters);

        ResultActions result = mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(command))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Bartek")))
                .andExpect(jsonPath("$.lastName", is("Nowak")))
                .andExpect(jsonPath("$.pesel", is("99041702398")))
                .andExpect(jsonPath("$.growth", is(173)))
                .andExpect(jsonPath("$.weight", is(84.00)))
                .andExpect(jsonPath("$.email", is("bartekN2@gmail.com")))
                .andExpect(jsonPath("$.nameOfUniversity", is("Politechnika Rzeszowska")))
                .andExpect(jsonPath("$.yearOfStudy", is(1)))
                .andExpect(jsonPath("$.fieldOfStudy", is("Informatyka")))
                .andExpect(jsonPath("$.amountOfScholarship", is(234.00)));

        String response = result.andReturn().getResponse().getContentAsString();
        StudentSimpleDto studentSimpleDto = mapper.readValue(response, StudentSimpleDto.class);

        Map<String, Object> paramsToUpdate = new HashMap<>();
        paramsToUpdate.put("nameOfUniversity", "Uniwesytet Rzeszowski");
        paramsToUpdate.put("yearOfStudy", 2);
        paramsToUpdate.put("fieldOfStudy", "Filozofia");
        paramsToUpdate.put("amountOfScholarship", 354.00);
        paramsToUpdate.put("firstName", "Bartek");
        paramsToUpdate.put("lastName", "Nowak");
        paramsToUpdate.put("pesel", "99041702398");
        paramsToUpdate.put("growth", 173);
        paramsToUpdate.put("weight", 83.00);
        paramsToUpdate.put("email", "bartekN2@gmail.com");

        UpdatePersonCommand updateCommand = new UpdatePersonCommand();
        updateCommand.setPersonType("student");
        updateCommand.setParameters(paramsToUpdate);

        mockMvc.perform(put("/api/people/{id}", studentSimpleDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(updateCommand))))
                .andExpect(status().isOk());

        ResultActions expectResult = mockMvc.perform(get("/api/people/{id}", studentSimpleDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String expectedResponse = expectResult.andReturn().getResponse().getContentAsString();
        Student student = mapper.readValue(expectedResponse, Student.class);

        assertEquals("Bartek", student.getFirstName());
        assertEquals("Nowak", student.getLastName());
        assertEquals("99041702398", student.getPesel());
        assertEquals(173, student.getGrowth());
        assertEquals(83.00, student.getWeight());
        assertEquals("bartekN2@gmail.com", student.getEmail());
        assertEquals("Uniwesytet Rzeszowski", student.getNameOfUniversity());
        assertEquals(2, student.getYearOfStudy());
        assertEquals("Filozofia", student.getFieldOfStudy());
        assertEquals(354.00, student.getAmountOfScholarship());


    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldFindPeopleByCriteria() throws Exception {
        PersonSearchRequest request = new PersonSearchRequest();
        request.setFirstName("Kacper");
        request.setLastName("Kowalski");
        request.setMinGrowth(160);
        request.setMinGrowth(180);

        mockMvc.perform(get("/api/people/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].firstName", is("Kacper")))
                .andExpect(jsonPath("$[0].lastName", is("Kowalski")))
                .andExpect(jsonPath("$[0].growth", allOf(greaterThanOrEqualTo(160), lessThanOrEqualTo(180))));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldDeletePersonById() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amountOfPension", 2234.00);
        parameters.put("yearsWorked", 46);
        parameters.put("firstName", "Zbigniew");
        parameters.put("lastName", "Kasak");
        parameters.put("pesel", "63102105498");
        parameters.put("growth", 172);
        parameters.put("weight", 75.00);
        parameters.put("email", "zbigniewK@gmail.com");

        CreatePersonCommand personCommand = new CreatePersonCommand();
        personCommand.setPersonType("retiree");
        personCommand.setParameters(parameters);


        ResultActions result = mockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(personCommand))))
                .andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        RetireeFullDto retireeFullDto = mapper.readValue(response, RetireeFullDto.class);

        mockMvc.perform(delete("/api/people/{id}", retireeFullDto.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/people/{id}", retireeFullDto.getId()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorsMessages[0]", is("Nie znaleziono encji o danym id")));
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldUploadPeopleFromCsvFile() throws Exception {
        MvcResult resultBefore = mockMvc.perform(get("/api/people/count"))
                .andExpect(status().isOk())
                .andReturn();
        Long countBefore = Long.parseLong(resultBefore.getResponse().getContentAsString());

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "people.csv",
                "text/csv",
                ("type,firstName,lastName,pesel,growth,weight,email," +
                        "nameOfUniversity,yearOfStudy,fieldOfStudy," +
                        "amountOfScholarship,dateOfEmployment,currentPosition," +
                        "currentSalary,amountOfPension,yearsWorked\n" +
                        "retiree,Jan,Kielniak,68030245685,175,72,janekn@gmail.com,,,,,,,,2342.32,42\n" +
                        "student,Maciek,Kowalczyk,98111545236,180,80,maciekKow@wp.pl,PRz,2,Energetyka,754.34,,,,,\n" +
                        "employee,Darek,Ozga,76050527465,185,95,darek.ozga@o2.pl,,,,,2015-02-20,Serwisant,7435.00,,")
                        .getBytes());

        mockMvc.perform(multipart("/api/people/upload").file(file))
                .andExpect(status().isOk());

        MvcResult resultAfter = mockMvc.perform(get("/api/people/count"))
                .andExpect(status().isOk())
                .andReturn();
        Long countAfter = Long.parseLong(resultAfter.getResponse().getContentAsString());

        assertEquals(countBefore + 3, countAfter);
    }

    @Test
    @WithMockUser(username = "importer", roles = "IMPORTER")
    public void shouldReturnImportStatus() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "people.csv",
                "text/csv",
                ("type,firstName,lastName,pesel,growth,weight,email," +
                        "nameOfUniversity,yearOfStudy,fieldOfStudy," +
                        "amountOfScholarship,dateOfEmployment,currentPosition," +
                        "currentSalary,amountOfPension,yearsWorked\n" +
                        "retiree,Marian,Czernikowski,67052545685,175,72,marianek@gmail.com,,,,,,,,2142.32,42\n" +
                        "student,Mateusz,Krawczyk,99022445236,180,80,mateuszk@gmail.com,PRz,3,Informatyka,454.34,,,,,\n" +
                        "employee,Robert,Zawada,83070727465,185,95,darek.zawada@wp.pl,,,,,2015-02-20,Monter,7435.00,,\n" +
                        "retiree,Adam,Nowak,66020245689,172,68,adam@o2.pl,,,,,,,,2142.32,42\n" +
                        "student,Anna,Kowalska,99052545236,165,60,anna.kowalska@gmail.com,AGH,3,Matematyka,454.34,,,,,\n" +
                        "employee,Andrzej,Wojak,87124827465,183,90,andrzej.wojak@wp.pl,,,,,2020-02-20,Architekt,7435.00,,\n")
                        .getBytes());

        ResultActions resultActions = mockMvc.perform(multipart("/api/people/upload").file(file))
                .andExpect(status().isOk());

        String importId = resultActions.andReturn().getResponse().getContentAsString();

        MvcResult result = mockMvc.perform(get("/api/people/status/" + importId))
                .andExpect(status().isOk())
                .andReturn();

        ImportStatusDto status = mapper.readValue(result.getResponse().getContentAsString(), ImportStatusDto.class);
        assertEquals(ImportStatus.COMPLETED.name(), status.getStatus());
    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}