package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class GreetingService {

    @Value("${micorservice.greeting-sevice.endpoints.endpoint.url}")
    private String URL;

    @Autowired
    @Lazy
    private RestTemplate rest;

    public GreetingService(RestTemplate rest) {
        this.rest = rest;
    }

    public String getGreeting() {
        return rest.getForObject(URL, String.class);
    }

    public String getGreeting(String locale) {
        return rest.getForObject(new StringBuilder().append(URL).append("/").append(locale).toString(), String.class);
    }
}
