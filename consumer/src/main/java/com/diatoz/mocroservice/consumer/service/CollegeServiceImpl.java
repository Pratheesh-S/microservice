package com.diatoz.mocroservice.consumer.service;

import com.diatoz.mocroservice.consumer.model.CollegeModel;
import com.diatoz.mocroservice.consumer.webClient.WebClientGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService{

    @Autowired
    WebClientGenerator webClientGenerator;
    private final String collegePath;


    Logger logger = LoggerFactory.getLogger(CollegeServiceImpl.class);

    public CollegeServiceImpl( @Value("${producer.college.path}") String collegePath) {
        this.collegePath = collegePath;
    }


    @Override
        public CollegeModel saveCollege (CollegeModel collegeModel)  {
        logger.info("Control inside the consumer College Service");
        WebClient webClient = webClientGenerator.getTheInstantUrl(collegePath);

        return webClient.post()
                .uri("/saveCollege")
                .bodyValue(collegeModel)
                .retrieve()
                .bodyToMono(CollegeModel.class)
                .block();


    }

        @Override
        public String removeCollege (Integer id)  {
            WebClient webClient = webClientGenerator.getTheInstantUrl(collegePath);

        return webClient.delete()
                .uri("/removeCollege/" + id)
                .retrieve().bodyToMono(String.class).block();

    }

        @Override
        public List<CollegeModel> getALlCollege () {
            WebClient webClient = webClientGenerator.getTheInstantUrl(collegePath);

            return webClient.get()
                .uri("/getCollege")
                .retrieve()
                .bodyToFlux(CollegeModel.class)
                .collectList().block();
    }

        @Override
        public String updateCollege (CollegeModel collegeModel){
            WebClient webClient = webClientGenerator.getTheInstantUrl(collegePath);

            return webClient.put()
                .uri("/updateCollege")
                .bodyValue(collegeModel)
                .retrieve().bodyToMono(String.class)
                .block();
    }

        @Override
        public CollegeModel getCollegeById (Integer id) {
            WebClient webClient = webClientGenerator.getTheInstantUrl(collegePath);

            return webClient.get()
                .uri("/getCollegeById/" + id)
                .retrieve()
                .bodyToMono(CollegeModel.class).block();
    }




}
