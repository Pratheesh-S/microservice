package com.diatoz.microservice.producer.dao;

import com.diatoz.microservice.producer.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<StudentEntity, Integer> {

    List<StudentEntity> findStudentsByCollege_CollegeId(Integer collegeId);


}
