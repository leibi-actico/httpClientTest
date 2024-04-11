package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.config.ClientConfiguration;
import com.actico.poc.httpclient.httpclienttest.model.Joke;
import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "openFeign-okhttp-service", url = "${jokes.url}", configuration = ClientConfiguration.class)
@Observed
public interface OpenfeignServiceOkHttp {

    @GetMapping("/")
    Joke getJoke();

}



