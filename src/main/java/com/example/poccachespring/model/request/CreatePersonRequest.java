package com.example.poccachespring.model.request;


import com.example.poccachespring.model.Person;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreatePersonRequest(
        @NotNull
        @NotEmpty
        String name,
        @NotNull
        @NotEmpty
        String email
) {
    public static Person toDomain(CreatePersonRequest createPersonRequest) {
        return Person.builder()
                .name(createPersonRequest.name())
                .email(createPersonRequest.email())
                .build();
    }
}
