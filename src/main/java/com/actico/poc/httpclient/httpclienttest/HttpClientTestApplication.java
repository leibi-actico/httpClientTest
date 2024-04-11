package com.actico.poc.httpclient.httpclienttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HttpClientTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpClientTestApplication.class, args);
    }

}
