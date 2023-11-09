package com.diatoz.mocroservice.consumer.customException;

public class ProducerNotHostedException extends RuntimeException{
    public ProducerNotHostedException(String message) {
        super(message);
    }
}
