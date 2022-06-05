package com.edu.transplantdataapi.presentation.handlers;

import com.edu.transplantdataapi.exceptions.ClassificationTreeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                getHttpStatus(e),
                request
        );
    }

    private HttpStatus getHttpStatus(RuntimeException e) {
        HttpStatus status;
        if (e instanceof ClassificationTreeException)
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        else status = HttpStatus.BAD_REQUEST;
        return status;
    }
}
