package com.muslimov.vlad.taskmanager.exception.handler;

import com.muslimov.vlad.taskmanager.dto.ResponseMessage;
import com.muslimov.vlad.taskmanager.exception.model.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> notFoundException(NotFoundException exception) {

        log.error(exception.getMessage(), exception);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ResponseMessage(exception.getMessage()));
    }
}