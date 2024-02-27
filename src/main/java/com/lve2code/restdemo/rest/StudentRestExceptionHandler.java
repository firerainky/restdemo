package com.lve2code.restdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    // add exception handling code here
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        StudentErrorResponse resp = new StudentErrorResponse();
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.setMessage(exception.getMessage());
        resp.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
        StudentErrorResponse resp = new StudentErrorResponse();
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.setMessage(exception.getMessage());
        resp.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
