package com.example.poccachespring.model.response;


import com.example.poccachespring.model.Person;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record PersonCreatedResponse(
        String id,
        String name,
        String email
) implements Serializable {
    public static PersonCreatedResponse fromDomain(Person person) {
        return PersonCreatedResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }
}
