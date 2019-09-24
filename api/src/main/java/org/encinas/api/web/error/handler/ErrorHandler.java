package org.encinas.api.web.error.handler;

import org.encinas.business.responses.RequestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
class HandlerException extends ResponseEntityExceptionHandler {
    private static final String WHITESPACE = " ";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder message = new StringBuilder();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            message = message.append(error.getDefaultMessage()).append(" for ").append(error.getField()).append(WHITESPACE);
        }

        RequestResponse requestResponse = new RequestResponse(HttpStatus.BAD_REQUEST, message.toString(), null);

        return new ResponseEntity<>(requestResponse, requestResponse.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        RequestResponse requestResponse = new RequestResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(requestResponse, requestResponse.getStatusCode());
    }
}
