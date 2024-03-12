package pl.kurs.sprawdzianfinalnyzarzyk.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper getModelMapper(Set<Converter> converters) {
        ModelMapper mapper = new ModelMapper();
        converters.forEach(mapper::addConverter);
        return mapper;
    }
}
