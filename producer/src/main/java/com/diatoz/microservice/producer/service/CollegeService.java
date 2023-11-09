package com.diatoz.microservice.producer.service;



import com.diatoz.microservice.producer.customException.ProducerIdException;
import com.diatoz.microservice.producer.model.CollegeModel;

import java.util.List;

public interface CollegeService  {
    CollegeModel saveCollege(CollegeModel collegeModel);

    String removeCollege(Integer id) throws ProducerIdException;

    List<CollegeModel> getALlCollege();

    String updateCollege(CollegeModel collegeModel) throws ProducerIdException;

    CollegeModel getCollegeById(Integer id) throws ProducerIdException;
}
