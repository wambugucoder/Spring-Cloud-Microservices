package com.example.clouddemo;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class GreetingsController {
    private static final Logger LOG = Logger.getLogger(GreetingsController.class.getName());

    private final Environment environment;

    public GreetingsController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping("/retrieve/{languageCode}")
    public String getGreeting(@PathVariable String languageCode){
        String greetings=environment.getProperty("app1.greetings."+languageCode);
        LOG.info("Language Code: " + languageCode);
        LOG.info("Greeting: " + greetings);
        return greetings;
    }

    @RequestMapping("/test")
    public String getGreeting(){
        String greetings=environment.getProperty("app1.greetings.NOR");
        LOG.info("Greeting: " + greetings);
        return greetings;
    }
}
