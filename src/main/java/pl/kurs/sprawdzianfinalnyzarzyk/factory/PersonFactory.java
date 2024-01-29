package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.creators.PersonCreator;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters.PersonUpdater;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonFactory {
    private final Map<String, PersonCreator> creators;
    private final Map<String, PersonUpdater> updaters;


    public PersonFactory(Set<PersonCreator> creators, Set<PersonUpdater> updaters) {
        this.creators = creators.stream().collect(Collectors.toMap(PersonCreator::getType, Function.identity()));
        this.updaters = updaters.stream().collect(Collectors.toMap(PersonUpdater::getType, Function.identity()));
    }

    public Person create(CreatePersonCommand command) {
        return creators.get(command.getPersonType()).create(command.getParameters());
    }

    public Person update(Person person, UpdatePersonCommand command) {
        return updaters.get(command.getPersonType()).update(person, command.getParameters());
    }

//    public Person createPersonFromCsv(String[] line){
//        return creators.get(line[0]).createPersonFromCsv(line);
//    }

}
