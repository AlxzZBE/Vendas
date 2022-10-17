package com.alex.spring.vendas.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionDetails {

    protected LocalDateTime timeStamp;
    protected String title;
    protected Integer status;
    protected String details;
    protected String developerMessage;
    private List<ExceptionErrors> errors;

    public ExceptionDetails(RuntimeException e, String title) {
        this.timeStamp = LocalDateTime.now();
        this.title = title;
        this.status = e.getClass().getAnnotation(ResponseStatus.class).value().value();
        this.details = e.getMessage();
    }

    public ExceptionDetails(RuntimeException e, String title, String developerMessage) {
        this.timeStamp = LocalDateTime.now();
        this.title = title;
        this.status = e.getClass().getAnnotation(ResponseStatus.class).value().value();
        this.details = e.getMessage();
        this.developerMessage = developerMessage;
    }

    public ExceptionDetails(MethodArgumentNotValidException e, String title) {
        this.timeStamp = LocalDateTime.now();
        this.title = title;
        this.status = e.getStatusCode().value();
        this.details = e.getBody().getDetail();
        this.errors = e.getFieldErrors().stream().map(ExceptionErrors::new).toList();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public List<ExceptionErrors> getErrors() {
        return errors;
    }
}