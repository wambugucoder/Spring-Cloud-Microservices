package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class WebController {
    private static final Logger LOG = Logger.getLogger(WebController.class.getName());

    private final NameService nameService;
    private final GreetingService greetingService;

    public WebController(NameService nameService, GreetingService greetingService) {
        this.nameService = nameService;
        this.greetingService = greetingService;
    }

    @GetMapping("/example")
    public String index(HttpServletRequest request) {
        String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
        String greeting =  new StringBuilder().append(greetingService.getGreeting(locale)).
                append(" ").append(nameService.getName()).toString();
        LOG.info("Greeting: " + greeting);
        LOG.info("Locale: " + locale);
        return greeting;
    }
}
