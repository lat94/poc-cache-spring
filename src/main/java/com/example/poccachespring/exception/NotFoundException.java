package com.example.poccachespring.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7034897190745766939L;

    public NotFoundException(String msg) {
        super(msg);
    }

}