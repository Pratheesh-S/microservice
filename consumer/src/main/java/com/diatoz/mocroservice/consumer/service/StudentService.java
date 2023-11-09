package com.diatoz.mocroservice.consumer.service;

import com.diatoz.mocroservice.consumer.model.StudentModel;

import java.util.List;

public  interface StudentService {
    Object saveStudent(StudentModel studentModel) ;

    String removeStudent(Integer id) ;

    List<StudentModel> getALlStudent();

    String updateStudent(StudentModel studentModel) ;

    StudentModel getStudentById(Integer id) ;
}
