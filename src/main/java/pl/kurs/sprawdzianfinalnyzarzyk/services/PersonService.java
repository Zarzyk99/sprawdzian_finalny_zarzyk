package pl.kurs.sprawdzianfinalnyzarzyk.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonFactory personFactory;

    public PersonService(PersonRepository personRepository, PersonFactory personFactory) {
        this.personRepository = personRepository;
        this.personFactory = personFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> findALl(){
        return personRepository.findAll();
    }

    @Transactional
    public Person createPerson(CreatePersonCommand command) {
        return personRepository.saveAndFlush(personFactory.create(command));
    }
}
