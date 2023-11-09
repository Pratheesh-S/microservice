package com.diatoz.microservice.producer.customException;

public class ProducerIdException extends Exception{
    public ProducerIdException(String message) {
        super(message);
    }
}
