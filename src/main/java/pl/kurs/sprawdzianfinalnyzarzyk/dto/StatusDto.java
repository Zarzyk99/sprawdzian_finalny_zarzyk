package pl.kurs.sprawdzianfinalnyzarzyk.dto;

import lombok.Getter;

@Getter
public class StatusDto {
    private String status;

    public StatusDto(String status) {
        this.status = status;
    }

}
