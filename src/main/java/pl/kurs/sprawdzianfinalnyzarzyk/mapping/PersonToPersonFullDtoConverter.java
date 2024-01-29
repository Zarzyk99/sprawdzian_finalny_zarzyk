package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.Converter;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.PersonFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

public interface PersonToPersonFullDtoConverter<T extends Person, Y extends PersonFullDto> extends Converter<T, Y> {
    Y getDtoType();

    Class<T> getBaseEntityClass();
}
