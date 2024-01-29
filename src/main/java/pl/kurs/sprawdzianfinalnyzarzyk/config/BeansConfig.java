package pl.kurs.sprawdzianfinalnyzarzyk.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper getModelMapper(Set<Converter> converters) {
        ModelMapper mapper = new ModelMapper();
        converters.forEach(mapper::addConverter);
        return mapper;
    }

//    @Bean
//    public AuditorAware<String> auditorProvider(){
//        return () -> Optional.of(SecurityContextHolder)
//    }
}
