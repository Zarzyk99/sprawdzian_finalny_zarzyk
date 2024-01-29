package pl.kurs.sprawdzianfinalnyzarzyk.factory.creators;

import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;

import java.util.Map;

public interface PersonCreator {
    String getType();

    Person create(Map<String, Object> parameters);

}
//TYP 0, imie 1, nazwisko 2, pesel 3, wzrost 4, waga 5, adres email 6,
// nazwa uczelni 7, rok studiów 8, kierunek studiów 9, wysokość stypendium 10,
//data rozpoczęcia zatrudnienia 11, aktualne stanowisko 12, aktualna pensja 13,
//wysokośc emerytury 14, ilość przepracowanych lat 15