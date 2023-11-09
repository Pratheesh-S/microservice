package com.diatoz.mocroservice.consumer.config;


import com.netflix.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestTemplateConfig {




    @Bean
    public WebClient.Builder getWebClientBuilder()
    {
        return WebClient.builder();
    }
}
