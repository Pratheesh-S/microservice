package com.diatoz.mocroservice.consumer.controller;

import com.diatoz.mocroservice.consumer.customException.DataNotProper;
import com.diatoz.mocroservice.consumer.customException.StudentDataException;
import com.diatoz.mocroservice.consumer.dataValidatorPack.StudentDataValidation;
import com.diatoz.mocroservice.consumer.model.StudentModel;
import com.diatoz.mocroservice.consumer.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client/student")
public class ConsumerStudentController {

    @Autowired
    StudentDataValidation studentDataValidation;

    @Autowired
    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(ConsumerStudentController.class);

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveStudent(@Validated @RequestBody StudentModel studentModel, BindingResult result) throws StudentDataException, DataNotProper, JsonProcessingException{
        validateTheStudent(studentModel,result);
        logger.info("control inside consumer");
        return  ResponseEntity.ok(studentService.saveStudent(studentModel));


    }


    @RequestMapping(value = "/getStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentModel>> getStudent() {

        return ResponseEntity.ok(studentService.getALlStudent());

    }

    @RequestMapping(value = "/getStudentById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentModel> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));

    }

    @RequestMapping(value = "/removeStudent/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeStudentById(@PathVariable Integer id) {

        return  ResponseEntity.ok(studentService.removeStudent(id));
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getStudentById(@Validated @RequestBody StudentModel studentModel, BindingResult result) throws StudentDataException, DataNotProper, JsonProcessingException {
        validateTheStudent(studentModel,result);
        return ResponseEntity.ok(studentService.updateStudent(studentModel));
    }

    public void validateTheStudent(StudentModel studentModel,BindingResult result) throws StudentDataException, DataNotProper, JsonProcessingException {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            logger.info(errorMap.toString());
            throw new StudentDataException(errorMap);
        }
        studentDataValidation.checkTheStudentData(studentModel);

    }


}
