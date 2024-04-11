package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.ExtendedJoke;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Observed
public class JokeService {

    private final RestClientService restClientService;
    private final OpenfeignService openfeignService;
    private final OpenfeignServiceOkHttp openfeignServiceOkHttp;

    public List<ExtendedJoke> getJokes() {
        var jokes = new ArrayList<ExtendedJoke>();

        jokes.add(ExtendedJoke.builder().serviceType("spring http interface").joke(restClientService.getJoke()).build());
        jokes.add(ExtendedJoke.builder().serviceType("Spring Cloud OpenFeign").joke(openfeignService.getJoke()).build());
        jokes.add(ExtendedJoke.builder().serviceType("Spring Cloud OpenFeign - okHTTP").joke(openfeignServiceOkHttp.getJoke()).build());

        return jokes;
    }
}
