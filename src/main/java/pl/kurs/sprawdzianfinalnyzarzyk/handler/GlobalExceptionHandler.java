package pl.kurs.sprawdzianfinalnyzarzyk.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.BadEntityException;
import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.BadIdException;
import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.CsvParsingException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadEntityException.class, BadIdException.class})
    public ResponseEntity<ExceptionResponseBody> handlerCustomException(RuntimeException e) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                List.of(e.getMessage()),
                "BAD REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponseBody> handlerEntityNotFoundException(EntityNotFoundException e) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                List.of(e.getMessage()),
                "BAD REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponseBody> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorsMessages = e.getFieldErrors().stream()
                .map(fe -> "field: " + fe.getField() + " / rejected value: '" + fe.getRejectedValue() + "' / message: " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                errorsMessages,
                "BAD REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ExceptionResponseBody> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMessage = e.getMessage();
        String detailMessage;
        if (errorMessage.contains("email")) {
            detailMessage = "Błąd podczas pobierania: Email już istnieje";
        } else {
            detailMessage = "Błąd podczas pobierania: Pesel już istnieje";
        }

        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                List.of(detailMessage),
                "CONFLICT",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    @ExceptionHandler({CsvParsingException.class})
    public ResponseEntity<ExceptionResponseBody> handleCsvParsingException(CsvParsingException e) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(
                List.of("Błąd podczas parsowania pliku csv: " + e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
