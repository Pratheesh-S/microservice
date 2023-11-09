package com.diatoz.mocroservice.consumer.webClient;

import com.diatoz.mocroservice.consumer.customException.ProducerNotHostedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

@Component
public class WebClientGenerator {
    private final WebClient.Builder webClientBuilder;
    private final DiscoveryClient discoveryClient;
    private final String hittingApplicationName;
    private final Logger logger = LoggerFactory.getLogger(WebClientGenerator.class);

    @Autowired
    public WebClientGenerator(WebClient.Builder webClientBuilder, DiscoveryClient discoveryClient, @Value("${hitting.application.name}")String hittingApplicationName) {
        this.webClientBuilder = webClientBuilder;
        this.discoveryClient = discoveryClient;
        this.hittingApplicationName = hittingApplicationName;
    }

    public WebClient getTheInstantUrl(String majorPath)  {
        List<ServiceInstance> listOfInstance = discoveryClient.getInstances(this.hittingApplicationName);
        if(listOfInstance.isEmpty())
        {
            throw new ProducerNotHostedException("Produeer not fount");
        }
        Random random = new Random();
        ServiceInstance serviceInstance = listOfInstance.get(random.nextInt(listOfInstance.size()));
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();


        String hittingUrl = "http://" + host + ":" + port + majorPath;
        logger.info("The url to hit is {}",hittingUrl);
        return webClientBuilder.baseUrl(hittingUrl).build();


    }
}
