package pl.kurs.sprawdzianfinalnyzarzyk.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dao.IPersonSearchDao;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.ImportStatusDto;
import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.CsvParsingException;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.ImportEntity;
import pl.kurs.sprawdzianfinalnyzarzyk.models.ImportStatus;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.ImportEntityRepository;
import pl.kurs.sprawdzianfinalnyzarzyk.repositories.PersonRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final IPersonSearchDao personSearchDao;
    private final PersonFactory personFactory;
    private final ImportEntityRepository importEntityRepository;

    public Page<Person> findAllPeople(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional
    public Person createPerson(CreatePersonCommand command) {
        return personRepository.saveAndFlush(personFactory.create(command));
    }

    public List<Person> searchByCriteria(PersonSearchRequest personSearchRequest) {
        return personSearchDao.findByCriteria(personSearchRequest);
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));
    }

    @Transactional
    public Person editPerson(Long id, UpdatePersonCommand command) {
        Person person = findById(id);
        return personRepository.saveAndFlush(personFactory.update(person, command));
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Async
    public CompletableFuture<Integer> uploadPeople(MultipartFile file) {
        ImportEntity status = new ImportEntity();
        importEntityRepository.save(status);

        if (file.isEmpty()) {
            status.setStatus(ImportStatus.FAILED);
            importEntityRepository.save(status);
            return CompletableFuture.completedFuture(status.getId());
        }

        Set<Person> people = parseCsv(file);

        status.setStatus(ImportStatus.IN_PROGRESS);
        importEntityRepository.save(status);

        try {
            people.forEach(personRepository::save);
            status.setStatus(ImportStatus.COMPLETED);
            status.setProcessedRows(people.size());
        } catch (DataIntegrityViolationException e) {
            status.setStatus(ImportStatus.FAILED);
            throw e;
        } finally {
            importEntityRepository.save(status);
        }

        return CompletableFuture.completedFuture(status.getId());
    }


    public long countPeople() {
        return personRepository.count();
    }

    public ImportStatusDto getImportStatus(final Integer importId) {
        var importBox = importEntityRepository.findById(importId);
        return importBox.map(this::toImportStatusDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Set<Person> parseCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            ConvertUtils.convert(new Converter() {
                @Override
                public Object convert(Class type, Object value) {
                    return LocalDate.parse((CharSequence) value, DateTimeFormatter.ISO_LOCAL_DATE);
                }
            }, LocalDate.class);

            HeaderColumnNameMappingStrategy<PeopleCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(PeopleCsvRepresentation.class);

            CsvToBean<PeopleCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<PeopleCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(personFactory::createPersonFromCsv)
                    .collect(Collectors.toSet());
        } catch (IOException | RuntimeException e) {
            throw new CsvParsingException("Failed to parse csv file: " + e.getMessage());
        }
    }

    private ImportStatusDto toImportStatusDto(final ImportEntity entity) {
        return ImportStatusDto.builder()
                .startDate(entity.getStartDate())
                .status(entity.getStatus().name())
                .processedRows(entity.getProcessedRows())
                .build();
    }

}
