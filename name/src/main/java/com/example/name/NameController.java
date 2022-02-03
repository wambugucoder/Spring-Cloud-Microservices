package com.example.name;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class NameController {
    private static final Logger LOG = Logger.getLogger(NameController.class.getName());

    @Value("${app2.name}")
    private String name;



    @RequestMapping("/myname")
    public String getName() {
        LOG.info("Name: " + name);
        return name;
    }
}
