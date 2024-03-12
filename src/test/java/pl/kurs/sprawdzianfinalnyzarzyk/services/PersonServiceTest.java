package pl.kurs.sprawdzianfinalnyzarzyk.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dao.IPersonSearchDao;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonDtoFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private IPersonSearchDao personSearchDao;

    @MockBean
    private PersonFactory personFactory;

    @MockBean
    private PersonDtoFactory personDtoFactory;

    @Test
    public void shouldFindAllPeople(){
        PageRequest pageable = PageRequest.of(0,10);
        Page<Person> page = mock(Page.class);
        when(personService.findAllPeople(pageable)).thenReturn(page);

        assertEquals(page, personService.findAllPeople(pageable));
    }

    @Test
    public void testCreatePerson() {
        CreatePersonCommand command = new CreatePersonCommand();
        Person person = personService.createPerson(command);
        when(personFactory.create(command)).thenReturn(person);
        when(personRepository.saveAndFlush(person)).thenReturn(person);

        assertEquals(person, personService.createPerson(command));
    }


}