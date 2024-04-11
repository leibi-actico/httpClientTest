package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.service.annotation.GetExchange;

@Observed
public interface RestClientService {

    @GetExchange
    Joke getJoke();
}
