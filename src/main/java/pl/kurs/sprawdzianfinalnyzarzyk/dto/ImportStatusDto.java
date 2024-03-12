package pl.kurs.sprawdzianfinalnyzarzyk.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ImportStatusDto implements Serializable {
    private LocalDateTime startDate;

    private long processedRows;

    private String status;
}