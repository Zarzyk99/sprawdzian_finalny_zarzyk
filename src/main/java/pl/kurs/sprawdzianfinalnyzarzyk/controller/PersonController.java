package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.ImportStatusDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.StatusDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.PersonFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.PersonSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonDtoFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.services.PersonService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final ModelMapper mapper;
    private final PersonDtoFactory personDtoFactory;


    @GetMapping
    public ResponseEntity<Page<PersonSimpleDto>> getAllPeople(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(personService.findAllPeople(pageable).map(this::toSimpleDto));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<PersonFullDto> addPerson(@RequestBody @Valid CreatePersonCommand command) {
        return ResponseEntity.status(CREATED).body(toFullDto(personService.createPerson(command)));
    }

    @GetMapping("{id}")
    public ResponseEntity getPerson(@PathVariable long id) {
        return ResponseEntity.ok(toFullDto(personService.findById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> findByCriteria(@RequestBody PersonSearchRequest personSearchRequest) {
        return ResponseEntity.status(OK).body(personService.searchByCriteria(personSearchRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonSimpleDto> updatePerson(@PathVariable Long id, @RequestBody @Valid UpdatePersonCommand command) {
        Person person = personService.editPerson(id, command);
        return ResponseEntity.ok(toSimpleDto(person));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusDto> deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.ok(new StatusDto("id: " + id + " deleted"));
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadPeople(@RequestPart("file") MultipartFile file) {
        CompletableFuture<Integer> result = personService.uploadPeople(file);
        return ResponseEntity.ok(result.join());
    }

    @GetMapping("/status/{importId}")
    public ResponseEntity<ImportStatusDto> getImportStatus(final @PathVariable("importId") Integer importId) {
        ImportStatusDto status = personService.getImportStatus(importId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPeople() {
        return ResponseEntity.ok(personService.countPeople());
    }

    public PersonFullDto toFullDto(Person person) {
        return mapper.map(person, personDtoFactory.findFullDtoConverter(person).getDtoType().getClass());
    }

    public PersonSimpleDto toSimpleDto(Person person) {
        return mapper.map(person, personDtoFactory.findSimpleDtoConverter(person).getDtoType().getClass());
    }
}
