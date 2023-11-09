package com.diatoz.microservice.producer.service;

import com.diatoz.microservice.producer.customException.ProducerIdException;
import com.diatoz.microservice.producer.dao.CollegeDao;
import com.diatoz.microservice.producer.dao.StudentDao;
import com.diatoz.microservice.producer.entity.CollegeEntity;
import com.diatoz.microservice.producer.entity.StudentEntity;
import com.diatoz.microservice.producer.model.CollegeModel;
import com.diatoz.microservice.producer.model.StudentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentDao studentDao;
    @Autowired
    CollegeDao collegeDao;

    @Override
    public StudentModel saveStudent(StudentModel studentModel) throws DataAccessException, ProducerIdException {
        logger.info("College Data {} ", studentModel);

        if (collegeDao.findById(studentModel.getCollege().getCollegeId()).isEmpty()) {
            throw new ProducerIdException(" College With given id not present in data base");
        }
        StudentEntity studentEntity = beanToEntityConvertor(studentModel);
        logger.info("Student Entity data {}",studentEntity);

        return entityToBeanConvertor(studentDao.save(studentEntity));
    }

    @Override
    public String removeStudent(Integer id) throws ProducerIdException,DataAccessException {

        if(studentDao.findById(id).isPresent())
        {
            studentDao.deleteById(id);
            return "User with user id" + id + " deleted successfully";
        }
        throw  new ProducerIdException("Student with given id not present");
    }

    @Override
    public List<StudentModel> getALlStudent() throws DataAccessException{
        List<StudentModel> allStudentModel = new ArrayList<>();
        for(StudentEntity studentEntity : studentDao.findAll())
        {
            allStudentModel.add(entityToBeanConvertor(studentEntity));
        }
        return allStudentModel;
    }

    @Override
    public String updateStudent(StudentModel studentModel) throws ProducerIdException,DataAccessException {
        if(studentDao.findById(studentModel.getStudentId()).isPresent())
        {
            if (collegeDao.findById(studentModel.getCollege().getCollegeId()).isEmpty()) {
                throw new ProducerIdException(" College With given id not present in data base");
            }
            studentDao.save(beanToEntityConvertor(studentModel));
            return "User with user id" + studentModel.getStudentId()+ " updated successfully";
        }
        throw  new ProducerIdException("Student with given id not present");

    }

    @Override
    public StudentModel getStudentById(Integer id) throws ProducerIdException,DataAccessException {
        Optional<StudentEntity> studentEntity = studentDao.findById(id);
        if(studentEntity.isPresent())
        {
          return  entityToBeanConvertor(studentEntity.get());
        }
        throw  new ProducerIdException("Student with given id not present");
    }

    public StudentModel entityToBeanConvertor(StudentEntity studentEntity)
    {
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(studentEntity,studentModel);
        if (studentEntity.getCollege() != null) {
            CollegeModel collegeModel = new CollegeModel();
            BeanUtils.copyProperties(studentEntity.getCollege(), collegeModel);
            studentModel.setCollege(collegeModel);
        }


        return studentModel;
    }
    public StudentEntity beanToEntityConvertor(StudentModel studentModel)
    {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentModel,studentEntity);
        if (studentModel.getCollege() != null) {
            CollegeEntity collegeEntity = new CollegeEntity();
            BeanUtils.copyProperties(studentModel.getCollege(), collegeEntity);
            studentEntity.setCollege(collegeEntity);
        }

        return studentEntity;
    }
}
