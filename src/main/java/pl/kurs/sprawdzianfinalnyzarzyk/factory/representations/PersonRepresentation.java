package pl.kurs.sprawdzianfinalnyzarzyk.factory.representations;

import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;

public interface PersonRepresentation {
    String getType();

    Person create(PeopleCsvRepresentation representation);
}
