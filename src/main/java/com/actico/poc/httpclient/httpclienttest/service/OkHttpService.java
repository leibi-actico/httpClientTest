package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import feign.RequestLine;
import io.micrometer.observation.annotation.Observed;

@Observed
public interface OkHttpService {

    @RequestLine("GET /")
    Joke getJoke();

}
