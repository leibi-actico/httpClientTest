package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "openFeign-service", url = "${jokes.url}")
@Observed
public interface OpenfeignService {

    @GetMapping("/")
    Joke getJoke();

}



