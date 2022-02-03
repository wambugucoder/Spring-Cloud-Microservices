package com.example.clouddemo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CloudDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(CloudDemoApplication.class, args);

    }

}
