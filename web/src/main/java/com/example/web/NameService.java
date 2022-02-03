package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class NameService {
    @Value("${micorservice.name-sevice.endpoints.endpoint.url}")
    private String URL;

    @Autowired
    @Lazy
    private final RestTemplate rest;

    public NameService(RestTemplate rest) {
        this.rest = rest;
    }

    public String getName() {
        return rest.getForObject(URL, String.class);
    }
}
