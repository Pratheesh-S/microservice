package com.diatoz.mocroservice.consumer.service;

import com.diatoz.mocroservice.consumer.model.CollegeModel;

import java.util.List;

public interface CollegeService  {
    CollegeModel saveCollege(CollegeModel collegeModel) ;

    String removeCollege(Integer id) ;

    List<CollegeModel> getALlCollege();

    String updateCollege(CollegeModel collegeModel) ;

    CollegeModel getCollegeById(Integer id);
}
