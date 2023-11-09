package com.diatoz.microservice.producer.controller;


import com.diatoz.microservice.producer.customException.ProducerIdException;
import com.diatoz.microservice.producer.model.StudentModel;
import com.diatoz.microservice.producer.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer/student")
public class ProducerStudentController {

    @Autowired
    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(ProducerStudentController.class);

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveStudent(@Validated @RequestBody StudentModel studentModel, BindingResult result) throws ProducerIdException {
            logger.info("Inside the producer");
            return ResponseEntity.ok(studentService.saveStudent(studentModel));


    }





    @RequestMapping(value = "/getStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentModel>> getStudent() {
        logger.info("Inside the producer");
        return ResponseEntity.ok(studentService.getALlStudent());

    }

    @RequestMapping(value = "/getStudentById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentModel> getStudentById(@PathVariable Integer id) throws ProducerIdException {
        logger.info("Inside the producer");
        return ResponseEntity.ok(studentService.getStudentById(id));

    }

    @RequestMapping(value = "/removeStudent/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeStudentById(@PathVariable Integer id) throws ProducerIdException {
        logger.info("Inside the producer");

        return  ResponseEntity.ok(studentService.removeStudent(id));
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getStudentById(@Validated @RequestBody StudentModel studentModel, BindingResult result) throws ProducerIdException {
        logger.info("Inside the producer");
        return ResponseEntity.ok(studentService.updateStudent(studentModel));
    }




}
