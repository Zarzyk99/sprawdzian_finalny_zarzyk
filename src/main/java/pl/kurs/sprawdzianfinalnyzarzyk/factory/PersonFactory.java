package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.CsvParsingException;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.creators.PersonCreator;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.representations.PersonRepresentation;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters.PersonUpdater;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonFactory {
    private final Map<String, PersonCreator> creators;
    private final Map<String, PersonUpdater> updaters;
    private final Map<String, PersonRepresentation> csvCreators;


    public PersonFactory(Set<PersonCreator> creators, Set<PersonUpdater> updaters, Set<PersonRepresentation> csvCreators) {
        this.creators = creators.stream().collect(Collectors.toMap(PersonCreator::getType, Function.identity()));
        this.updaters = updaters.stream().collect(Collectors.toMap(PersonUpdater::getType, Function.identity()));
        this.csvCreators = csvCreators.stream().collect(Collectors.toMap(PersonRepresentation::getType, Function.identity()));
    }


    public Person create(@Valid CreatePersonCommand command) {
        return creators.get(command.getPersonType()).create(command.getParameters());
    }

    public Person update(Person person, UpdatePersonCommand command) {
        return updaters.get(command.getPersonType()).update(person, command.getParameters());
    }

//    public Person createPersonFromCsv(String[] line){
//        return creators.get(line[0]).createPersonFromCsv(line);
//    }

}
