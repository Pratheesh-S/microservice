package com.diatoz.microservice.producer.exceptionHandler;


import com.diatoz.microservice.producer.customException.ProducerIdException;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({ProducerIdException.class})
    public ResponseEntity<String> showException(ProducerIdException idException) {
        logger.error("The Exception occure becauser {}", ResponseEntity.badRequest().body(idException.getMessage()));
        return ResponseEntity.badRequest().body(idException.getMessage());
    }




    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> databaseException(JsonProcessingException jsonProcessingException) {
        logger.error("The Exception occure becauser {}",jsonProcessingException.getMessage() );

        return new ResponseEntity<>(Objects.requireNonNull(jsonProcessingException.getCause()).getMessage(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> getExceptionMessage(Exception e) {
        logger.error("The Exception occure becauser {}",e.getMessage() );

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
