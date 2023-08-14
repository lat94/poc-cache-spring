package com.example.poccachespring.controller;

import com.example.poccachespring.model.request.CreatePersonRequest;
import com.example.poccachespring.model.response.PersonCreatedResponse;
import com.example.poccachespring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonCreatedResponse> create(@RequestBody CreatePersonRequest createPersonRequest) {
        var personCreated = personService.create(createPersonRequest);
        return ResponseEntity.ok(personCreated);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonCreatedResponse> getById(@PathVariable("id") String id) {
        var foundPerson = personService.getById(id);
        return ResponseEntity.ok(foundPerson);
    }
}
