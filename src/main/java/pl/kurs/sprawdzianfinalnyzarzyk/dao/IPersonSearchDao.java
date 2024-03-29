package pl.kurs.sprawdzianfinalnyzarzyk.dao;

import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;

import java.util.List;

public interface IPersonSearchDao {
    List<Person> findByCriteria(PersonSearchRequest request);
}
