package com.diatoz.mocroservice.consumer.controller;

import com.diatoz.mocroservice.consumer.customException.DataNotProper;

import com.diatoz.mocroservice.consumer.customException.StudentDataException;
import com.diatoz.mocroservice.consumer.dataValidatorPack.CollegeDataValidator;
import com.diatoz.mocroservice.consumer.model.CollegeModel;
import com.diatoz.mocroservice.consumer.service.CollegeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/college")
public class ConsumerCollegeController {

    @Autowired
    CollegeDataValidator collegeDataValidation;

    @Autowired
    CollegeService collegeService;
    Logger logger = LoggerFactory.getLogger(ConsumerCollegeController.class);

    @RequestMapping(value = "/saveCollege", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollegeModel> saveCollege( @RequestBody CollegeModel collegeModel) throws DataNotProper, JsonProcessingException, StudentDataException {
        validateTheCollege(collegeModel);
        return ResponseEntity.ok(collegeService.saveCollege(collegeModel));


    }


    @RequestMapping(value = "/getCollege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CollegeModel>> getCollege() {

        return ResponseEntity.ok(collegeService.getALlCollege());

    }

    @RequestMapping(value = "/getCollegeById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollegeModel> getCollegeById(@PathVariable Integer id) {
        return ResponseEntity.ok(collegeService.getCollegeById(id));

    }

    @RequestMapping(value = "/removeCollege/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeCollegeById(@PathVariable Integer id)  {
        return  ResponseEntity.ok(collegeService.removeCollege(id));
    }

    @RequestMapping(value = "/updateCollege", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getCollegeById(@Validated @RequestBody CollegeModel collegeModel, BindingResult result) throws StudentDataException, DataNotProper, JsonProcessingException{
        validateTheCollege(collegeModel);
        return ResponseEntity.ok(collegeService.updateCollege(collegeModel));
    }

    public void validateTheCollege(CollegeModel collegeModel) throws StudentDataException, DataNotProper, JsonProcessingException {

        collegeDataValidation.checkTheCollegeData(collegeModel);

    }
}
