package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.ExtendedJoke;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JokeService {

    private final RestClientService restClientService;

    public List<ExtendedJoke> getJokes() {
        var jokes = new ArrayList<ExtendedJoke>();

        jokes.add(ExtendedJoke.builder().serviceType("restClientService").joke(restClientService.getJoke()).build());

        return jokes;
    }
}
