package pl.kurs.sprawdzianfinalnyzarzyk.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dao.IPersonSearchDao;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonDtoFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final IPersonSearchDao personSearchDao;
    private final PersonFactory personFactory;
    private final PersonDtoFactory personDtoFactory;

    public Page<Person> findAllPeople(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional
    public Person createPerson(CreatePersonCommand command) {
        return personRepository.saveAndFlush(personFactory.create(command));
    }

    public List<Person> searchByCriteria(PersonSearchRequest personSearchRequest) {
        return personSearchDao.findByCriteria(personSearchRequest);
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));
    }

    @Transactional
    public Person editPerson(Long id, UpdatePersonCommand command) {
        Person person = findById(id);
        return personRepository.saveAndFlush(personFactory.update(person, command));
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

//    @Transactional
//    public void uploadPeople(Stream<String> lines) throws IOException {
//        lines
//                .skip(1)
//                .map(line -> line.split(", "))
//                .map(this::createPersonFromCsv)
//                .forEach(personRepository::save);
//    }

//    private Person createPersonFromCsv(String[] line) {
//        return personFactory.createPersonFromCsv(line);
//    }
}
