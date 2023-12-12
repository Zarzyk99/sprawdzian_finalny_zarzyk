package pl.kurs.sprawdzianfinalnyzarzyk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.kurs.sprawdzianfinalnyzarzyk.command.CreatePersonCommand;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.services.PersonService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(){
        return ResponseEntity.ok(personService.findALl());
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Person> addPerson(@RequestBody CreatePersonCommand command){
        return ResponseEntity.status(CREATED).body(personService.createPerson(command));
    }
}
