package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.command.UpdatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.full.PersonFullDto;
import pl.kurs.sprawdzianfinalnyzarzyk.dto.simple.PersonSimpleDto;
import pl.kurs.sprawdzianfinalnyzarzyk.factory.PersonDtoFactory;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Employee;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Position;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;
import pl.kurs.sprawdzianfinalnyzarzyk.services.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class  PersonController {

    private final PersonService personService;
    private final ModelMapper mapper;
    private final PersonDtoFactory personDtoFactory;


    @GetMapping
    public ResponseEntity<Page<PersonSimpleDto>> getAllPeople(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(personService.findAllPeople(pageable).map(this::toSimpleDto));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<PersonFullDto> addPerson(@RequestBody CreatePersonCommand command) {
        return ResponseEntity.status(CREATED).body(toFullDto(personService.createPerson(command)));
    }

    @GetMapping("{id}")
    public ResponseEntity getPerson(@PathVariable long id) {
        return ResponseEntity.ok(toSimpleDto(personService.findById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> findByCriteria(@RequestBody PersonSearchRequest personSearchRequest) {
        return ResponseEntity.status(OK).body(personService.searchByCriteria(personSearchRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonCommand command) {
        Person person = personService.editPerson(id, command);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFromCsv(@RequestParam("file") MultipartFile file) throws IOException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(file.getInputStream())).lines();
        personService.uploadPeople(lines);
        return file.isEmpty() ?
                new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity(HttpStatus.OK);
    }

    public PersonFullDto toFullDto(Person person) {
        return mapper.map(person, personDtoFactory.findFullDtoConverter(person).getDtoType().getClass());
    }

    public PersonSimpleDto toSimpleDto(Person person){
        return mapper.map(person, personDtoFactory.findSimpleDtoConverter(person).getDtoType().getClass());
    }
}
//Chcemy miec endpoint do zarządzania stanowiskami danego pracownika.
//pracownik na danym stanowisku moze pracowac <od, do>, na stanowisku o nazwie XYZ i otrzymujac pensje ABC.
//stanowiska nie mogą się pokrywać datami (daty nie moga na siebie nachodzic)
//nalezy tez zabezpieczyc przypisywanie stanowiska w taki sposob aby daty nie mogly sie pokrywac z istniejacymi stanowiskami.

//    @GetMapping("/download")
//    public ResponseEntity downloadPeople(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=person.csv");
//        String personText = personService.getPeopleAxText();
//        ByteArrayResource resource = new ByteArrayResource(personText.getBytes(StandardCharsets.UTF_8));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(personText.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }