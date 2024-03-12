package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.EmploymentDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employment;

@Service
public class EmploymentToEmploymentDtoConverter implements Converter<Employment, EmploymentDto> {
    @Override
    public EmploymentDto convert(MappingContext<Employment, EmploymentDto> context) {
        Employment source = context.getSource();
        return EmploymentDto.builder()
                .id(source.getId())
                .position(source.getPosition().getPositionName())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .build();
    }
}
