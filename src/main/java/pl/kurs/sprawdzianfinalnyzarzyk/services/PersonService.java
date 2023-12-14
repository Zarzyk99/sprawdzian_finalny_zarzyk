package pl.kurs.sprawdzianfinalnyzarzyk.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dao.IPersonSearchDao;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final IPersonSearchDao personSearchDao;

    private final PersonFactory personFactory;

    public PersonService(PersonRepository personRepository, IPersonSearchDao personSearchDao, PersonFactory personFactory) {
        this.personRepository = personRepository;
        this.personSearchDao = personSearchDao;
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

    public List<Person> searchByCriteria(PersonSearchRequest personSearchRequest){
        return personSearchDao.findByCriteria(personSearchRequest);
    }


}
