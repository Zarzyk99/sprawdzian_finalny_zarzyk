package pl.kurs.sprawdzianfinalnyzarzyk.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionResponseBody {
    private List<String> errorsMessages;

    private String errorCode;

    private LocalDateTime timestamp;
}
