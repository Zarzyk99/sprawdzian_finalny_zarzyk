package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.mapping.PersonToPersonFullDtoConverter;
import pl.kurs.sprawdzianfinalnyzarzyk.mapping.PersonToPersonSimpleDtoConverter;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonDtoFactory {
    private final Map<Class<? extends Person>, PersonToPersonFullDtoConverter> fullConverters;
    private final Map<Class<? extends Person>, PersonToPersonSimpleDtoConverter> simpleConverters;

    public PersonDtoFactory(Set<PersonToPersonFullDtoConverter> fullConverters,
                            Set<PersonToPersonSimpleDtoConverter> simpleConverters) {
        this.fullConverters = fullConverters.stream()
                .collect(Collectors.toMap(PersonToPersonFullDtoConverter::getBaseEntityClass, Function.identity()));
        this.simpleConverters = simpleConverters.stream()
                .collect(Collectors.toMap(PersonToPersonSimpleDtoConverter::getBaseEntityClass, Function.identity()));
    }

    public PersonToPersonFullDtoConverter findFullDtoConverter(Person person) {
        return Optional.ofNullable(fullConverters.get(person.getClass()))
                .orElseThrow();
    }

    public PersonToPersonSimpleDtoConverter findSimpleDtoConverter(Person person) {
        return Optional.ofNullable(simpleConverters.get(person.getClass()))
                .orElseThrow();
    }
}


