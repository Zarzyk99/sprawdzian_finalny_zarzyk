package pl.kurs.sprawdzianfinalnyzarzyk.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public interface Parametric {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    default String getStringParameter(String name, Map<String, Object> parameters) {
        return (String) parameters.get(name);
    }

    default Integer getIntegerParameters(String name, Map<String, Object> parameters) {
        return (Integer) parameters.get(name);
    }

    default Double getDoubleParameters(String name, Map<String, Object> parameters) {
        return (Double) parameters.get(name);
    }

    default LocalDate getLocalDateParameters(String name, Map<String, Object> parameters) {
        return LocalDate.parse((String) parameters.get(name), formatter);
    }
}
