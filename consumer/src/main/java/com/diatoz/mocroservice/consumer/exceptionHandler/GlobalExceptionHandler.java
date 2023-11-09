package com.diatoz.mocroservice.consumer.exceptionHandler;


import com.diatoz.mocroservice.consumer.customException.DataNotProper;
import com.diatoz.mocroservice.consumer.customException.ProducerNotHostedException;
import com.diatoz.mocroservice.consumer.customException.StudentDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice

public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(DataNotProper.class)
    public ResponseEntity<String> getDataNotProper(DataNotProper dataNotProper) {

        return new ResponseEntity<>(dataNotProper.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProducerNotHostedException.class)
    public ResponseEntity<String> producerNotHosted(ProducerNotHostedException ex)
    {
        logger.error("The Error occurred due to {}",ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> databaseException(JsonProcessingException jsonProcessingException) {

        return new ResponseEntity<>(Objects.requireNonNull(jsonProcessingException.getCause()).getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentDataException.class)
    public ResponseEntity<Map<String, String>> getStudentDataException(StudentDataException studentDataException) {
        return new ResponseEntity<>(studentDataException.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> exceptionFromProducer(WebClientResponseException ex)
    {
        logger.error("The exception is occurred due to {}", ex.getResponseBodyAsString() );
        return new ResponseEntity<>(ex.getResponseBodyAsString(),HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> getExceptionMessage(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
