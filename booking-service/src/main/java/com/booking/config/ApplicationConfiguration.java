package com.booking.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
