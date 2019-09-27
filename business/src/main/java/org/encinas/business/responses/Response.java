package org.encinas.business.responses;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private HttpStatus status;
    private String message;
    private Object errors;
    private Object data;

    private Response(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.errors = builder.errors;
        this.data = builder.data;
    }

    public static class Builder {
        private HttpStatus status;
        private String message;
        private Object errors;
        private Object data;

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder errors(Object errors) {
            this.errors = errors;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Response builder() {
            return new Response(this);
        }
    }
}
