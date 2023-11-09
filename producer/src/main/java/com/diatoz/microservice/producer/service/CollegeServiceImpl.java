package com.diatoz.microservice.producer.service;


import com.diatoz.microservice.producer.customException.ProducerIdException;
import com.diatoz.microservice.producer.dao.CollegeDao;
import com.diatoz.microservice.producer.dao.StudentDao;
import com.diatoz.microservice.producer.entity.CollegeEntity;
import com.diatoz.microservice.producer.entity.StudentEntity;
import com.diatoz.microservice.producer.model.CollegeModel;
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
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    CollegeDao collegeDao;
    @Autowired
    StudentDao studentDao;
    private final Logger logger = LoggerFactory.getLogger(CollegeServiceImpl.class);
    @Override
    public CollegeModel saveCollege(CollegeModel collegeModel) {

        return convertEntityToBean(collegeDao.save(convertBeanToEntity(collegeModel)));

    }

    @Override
    public String removeCollege(Integer id) throws ProducerIdException, DataAccessException {
        if(collegeDao.findById(id).isPresent())
        {
            List<StudentEntity> allStudentWithCollegeId = studentDao.findStudentsByCollege_CollegeId(id);
            long studentCount = allStudentWithCollegeId.stream().filter(studentEntity -> !studentEntity.isGraduated()).count();
            if (studentCount > 0) {
                throw new ProducerIdException("Unable to delete the College with a given id " + id +
                        " because there are "
                        + studentCount + " Student studying in the college ");
            } else {
                logger.info("All student are already Graduated So we can remove college by remove student if any");
                for (StudentEntity studentEntity : allStudentWithCollegeId) {
                    studentDao.deleteById(studentEntity.getStudentId());
                }
                collegeDao.deleteById(id);
                return "College with given id " + id + " removed successfully";
            }

        }
        throw  new ProducerIdException("The College with given Id not Present");
    }

    @Override
    public List<CollegeModel> getALlCollege() throws DataAccessException {
        List<CollegeModel> allCollegeModel = new ArrayList<>();
        for(CollegeEntity collegeEntity : collegeDao.findAll())
        {
            allCollegeModel.add(convertEntityToBean(collegeEntity));
        }
        return allCollegeModel;
    }

    @Override
    public String updateCollege(CollegeModel collegeModel) throws ProducerIdException,DataAccessException {
        Optional<CollegeEntity> collegeEntity = collegeDao.findById(collegeModel.getCollegeId());
        if(collegeEntity.isPresent())
        {
           collegeDao.save(collegeEntity.get());
            return "College data updated successfully";
        }
        throw  new ProducerIdException("The College with given Id not Present");
    }

    @Override
    public CollegeModel getCollegeById(Integer id) throws ProducerIdException,DataAccessException {
        Optional<CollegeEntity> collegeEntity = collegeDao.findById(id);
        if(collegeEntity.isPresent())
        {
           return convertEntityToBean(collegeEntity.get());
        }
        throw  new ProducerIdException("The College with given Id not Present");
    }



    public CollegeModel convertEntityToBean(CollegeEntity collegeEntity)
    {
        CollegeModel collegeModel = new CollegeModel();
        BeanUtils.copyProperties(collegeEntity,collegeModel);
        return collegeModel;
    }
    public CollegeEntity convertBeanToEntity(CollegeModel collegeModel)
    {
    CollegeEntity collegeEntity = new CollegeEntity();
    BeanUtils.copyProperties(collegeModel,collegeEntity);
    return collegeEntity;
    }
}
