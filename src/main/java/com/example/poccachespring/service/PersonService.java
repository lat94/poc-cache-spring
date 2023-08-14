package com.example.poccachespring.service;

import com.example.poccachespring.exception.NotFoundException;
import com.example.poccachespring.model.Person;
import com.example.poccachespring.model.request.CreatePersonRequest;
import com.example.poccachespring.model.response.PersonCreatedResponse;
import com.example.poccachespring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonCreatedResponse create(CreatePersonRequest createPersonRequest) {
        var personToBeSaved = CreatePersonRequest.toDomain(createPersonRequest);
        var savedPerson = personRepository.save(personToBeSaved);
        return PersonCreatedResponse.fromDomain(savedPerson);
    }

    @Cacheable("person")
    public PersonCreatedResponse getById(String id) {
        var foundPerson = personRepository.findById(id);
        return foundPerson.stream()
                .findFirst()
                .map(PersonCreatedResponse::fromDomain)
                .orElseThrow(() -> new NotFoundException("could not find person with given id: " + id));
    }
}
