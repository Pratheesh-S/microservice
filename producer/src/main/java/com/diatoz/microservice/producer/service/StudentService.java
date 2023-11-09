package com.diatoz.microservice.producer.service;


import com.diatoz.microservice.producer.customException.ProducerIdException;
import com.diatoz.microservice.producer.model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.List;

public  interface StudentService {
    StudentModel saveStudent(StudentModel studentModel) throws ProducerIdException;

    String removeStudent(Integer id) throws ProducerIdException;

    List<StudentModel> getALlStudent();

    String updateStudent(StudentModel studentModel) throws ProducerIdException;

    StudentModel getStudentById(Integer id) throws ProducerIdException;
}
