package com.diatoz.mocroservice.consumer.service;

import com.diatoz.mocroservice.consumer.model.StudentModel;
import com.diatoz.mocroservice.consumer.webClient.WebClientGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private WebClientGenerator webClientGenerator;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final String studentPath;

    public StudentServiceImpl(@Value("${producer.student.path}") String studentPath) {
        this.studentPath = studentPath;
    }


    @Override
    public Object saveStudent(StudentModel studentModel) {

            logger.info(" control Inside Student Service of consumer");
        WebClient webClient = webClientGenerator.getTheInstantUrl(studentPath);
            return webClient.post()
                    .uri("/saveStudent")
                    .bodyValue(studentModel)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

    }

    @Override
    public String removeStudent(Integer id)  {
        logger.info(" control Inside Student Service of consumer");

        WebClient webClient = webClientGenerator.getTheInstantUrl(studentPath);

        return webClient.delete()
                .uri("/removeStudent/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public List<StudentModel> getALlStudent() {
        WebClient webClient = webClientGenerator.getTheInstantUrl(studentPath);

        return webClient.get()
                .uri("/getStudent")
                .retrieve()
                .bodyToFlux(StudentModel.class)
               .collectList()
                .block();
    }

    @Override
    public String updateStudent(StudentModel studentModel)  {
        WebClient webClient = webClientGenerator.getTheInstantUrl(studentPath);
        return webClient.put()
                .uri("/updateStudent")
                .bodyValue(studentModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public StudentModel getStudentById(Integer id)  {
        WebClient webClient = webClientGenerator.getTheInstantUrl(studentPath);
        return webClient.get()
                .uri("/getStudentById/" + id)
                .retrieve().bodyToMono(StudentModel.class).block();
    }
}
