package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonFactory {
    private final Map<String, PersonCreator> creators;

    public PersonFactory(Set<PersonCreator> creators) {
        this.creators = creators.stream().collect(Collectors.toMap(PersonCreator::getType, Function.identity()));
    }

    public Person create(CreatePersonCommand command){
        return creators.get(command.getPersonType()).create(command.getParameters());
    }
}
