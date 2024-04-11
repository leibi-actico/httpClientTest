package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import org.springframework.web.service.annotation.GetExchange;

public interface RestClientService {

    @GetExchange
    Joke getJoke();
}
