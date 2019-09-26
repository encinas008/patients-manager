package org.encinas.business.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class Response {
    private HttpStatus statusCode;
    private String message;
    private Object errors;
    private Object data;

    public Response(HttpStatus httpStatus, String localizedMessage, Object errors) {
        this.statusCode = httpStatus;
        this.message = localizedMessage;
        this.errors = errors;
    }
}
