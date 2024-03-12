package pl.kurs.sprawdzianfinalnyzarzyk.factory.creators;

import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

public interface PersonCreator {
    String getType();

    Person create(Map<String, Object> parameters);
}
