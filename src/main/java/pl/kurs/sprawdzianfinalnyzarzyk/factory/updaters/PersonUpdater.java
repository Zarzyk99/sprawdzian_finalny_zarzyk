package pl.kurs.sprawdzianfinalnyzarzyk.factory.updaters;

import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

public interface PersonUpdater {
    String getType();

    Person update(Person person, Map<String, Object> parameters);
}
