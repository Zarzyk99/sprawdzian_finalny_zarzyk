package pl.kurs.sprawdzianfinalnyzarzyk.exceptions;

public class CsvParsingException extends RuntimeException{
    public CsvParsingException(String message) {
        super(message);
    }
}
