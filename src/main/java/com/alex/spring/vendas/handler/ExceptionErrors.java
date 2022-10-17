package com.alex.spring.vendas.handler;

import org.springframework.validation.FieldError;

public class ExceptionErrors {

    private String field;
    private String message;
    private String rejectedValue;

    public ExceptionErrors(FieldError error) {
        this.field = error.getField();
        this.message = error.getDefaultMessage();
        this.rejectedValue = error.getRejectedValue().toString();
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}