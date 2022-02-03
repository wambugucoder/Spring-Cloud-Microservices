package com.example.servicegateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/greetings-fallback")
    public Mono<String> GetGreetingFallback(){
        return Mono.just("We Cannot retrieve greetings at this moment");
    }
    @RequestMapping("/name-fallback")
    public Mono<String> GetNameFallback(){
        return Mono.just("We Cannot retrieve Name at this moment");
    }
    @RequestMapping("/web-fallback")
    public Mono<String> GetWebFallback() {
        return Mono.just("We Cannot retrieve Greetings and name at this moment");
    }

    }
