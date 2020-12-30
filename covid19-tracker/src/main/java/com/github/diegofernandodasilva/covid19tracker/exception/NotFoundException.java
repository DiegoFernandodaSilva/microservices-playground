package com.github.diegofernandodasilva.covid19tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO create exception handler(ds 27.12.2020)
@ResponseStatus(HttpStatus.FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7718828512143293558L;

    public NotFoundException(String message) {
        super(message);
    }
}
