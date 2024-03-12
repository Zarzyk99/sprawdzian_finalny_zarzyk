package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.RetireeFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Retiree;

@Service
public class RetireeToRetireeFullDtoConverter implements PersonToPersonFullDtoConverter<Retiree, RetireeFullDto> {

    @Override
    public RetireeFullDto convert(MappingContext<Retiree, RetireeFullDto> context) {
        Retiree source = context.getSource();
        return RetireeFullDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .pesel(source.getPesel())
                .growth(source.getGrowth())
                .weight(source.getWeight())
                .email(source.getEmail())
                .amountOfPension(source.getAmountOfPension())
                .yearsWorked(source.getYearsWorked())
                .build();
    }

    @Override
    public RetireeFullDto getDtoType() {
        return RetireeFullDto.builder().build();
    }

    @Override
    public Class<Retiree> getBaseEntityClass() {
        return Retiree.class;
    }
}
