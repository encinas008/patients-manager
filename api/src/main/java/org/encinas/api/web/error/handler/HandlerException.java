package org.encinas.api.web.error.handler;

import org.encinas.business.exceptions.DuplicatedDniException;
import org.encinas.business.responses.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class HandlerException extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String VALIDATION_ERROR = "Occurred an error of validation";
    private final static String UNKNOWN_ERROR = "Occurred an unknown error";
    private final static String DUPLICATED_ERROR = "Occurred an error of duplication of value";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error(VALIDATION_ERROR, ex);

        Response response = new Response.Builder().message(VALIDATION_ERROR).errors(errors)
                                                  .status(HttpStatus.BAD_REQUEST).builder();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({ DuplicatedDniException.class })
    public ResponseEntity<Object> handleDuplicatedDni(DuplicatedDniException ex, WebRequest request) {
        log.error(DUPLICATED_ERROR, ex);

        Response response = new Response.Builder().message(DUPLICATED_ERROR).errors(ex.getMessage())
                                                  .status(HttpStatus.CONFLICT).builder();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<Object> handleGlobalExeption(Exception ex, WebRequest request) {
        log.error(UNKNOWN_ERROR, ex);
        Response response = new Response.Builder().message(UNKNOWN_ERROR).status(HttpStatus.INTERNAL_SERVER_ERROR).builder();

        return new ResponseEntity<>(response, response.getStatus());
    }
}
