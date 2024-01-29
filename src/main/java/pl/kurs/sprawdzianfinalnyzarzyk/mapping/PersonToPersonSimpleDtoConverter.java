package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.Converter;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.PersonSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

public interface PersonToPersonSimpleDtoConverter<T extends Person, Y extends PersonSimpleDto> extends Converter<T, Y> {
    Y getDtoType();

    Class<T> getBaseEntityClass();
}
