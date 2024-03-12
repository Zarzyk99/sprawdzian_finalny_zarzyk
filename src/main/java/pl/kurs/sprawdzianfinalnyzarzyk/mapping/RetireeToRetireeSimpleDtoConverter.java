package pl.kurs.sprawdzianfinalnyzarzyk.mapping;

import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.RetireeSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Retiree;

@Service
public class RetireeToRetireeSimpleDtoConverter implements PersonToPersonSimpleDtoConverter<Retiree, RetireeSimpleDto> {
    @Override
    public RetireeSimpleDto convert(MappingContext<Retiree, RetireeSimpleDto> context) {
        Retiree source = context.getSource();
        return RetireeSimpleDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .build();
    }

    @Override
    public RetireeSimpleDto getDtoType() {
        return RetireeSimpleDto.builder().build();
    }

    @Override
    public Class<Retiree> getBaseEntityClass() {
        return Retiree.class;
    }
}
