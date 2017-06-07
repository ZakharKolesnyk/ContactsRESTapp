package com.gmail.kolesnyk.zakhar.handlers;

import com.gmail.kolesnyk.zakhar.service.userService.exeption.NoChangesAfterRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String processError() {
        return "{\"status\": \"unexpected error\"}";
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NoChangesAfterRequestException.class)
    public String registrationError(NoChangesAfterRequestException e) {
        return "{\"status\": \"" + e.getMessage() + "\"}";
    }
}
