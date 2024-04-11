package com.actico.poc.httpclient.httpclienttest.service;

import com.actico.poc.httpclient.httpclienttest.model.ExtendedJoke;
import com.actico.poc.httpclient.httpclienttest.model.Joke;
import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextSnapshotFactory;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Observed
public class JokeService {

    private final RestClientService restClientService;
    private final OpenfeignService openfeignService;
    private final OpenfeignServiceOkHttp openfeignServiceOkHttp;


    private final ExecutorService executor =
            ContextExecutorService.wrap(
                    Executors.newVirtualThreadPerTaskExecutor(),
                    () -> ContextSnapshotFactory.builder().build().captureAll());

    public List<ExtendedJoke> getJokes() {
        var jokes = new ArrayList<ExtendedJoke>();
        getCalls().forEach(extendedJokeCall -> {
            try {
                jokes.add(ExtendedJoke.builder().serviceType(extendedJokeCall.serviceType).joke(extendedJokeCall.jokeCall.get()).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return jokes;
    }

    private @NotNull List<ExtendedJokeCall> getCalls() {
        return List.of(new ExtendedJokeCall("spring http interface",
                        executor.submit(restClientService::getJoke)),
                new ExtendedJokeCall("Spring Cloud OpenFeign", executor.submit(openfeignService::getJoke)),
                new ExtendedJokeCall("Spring Cloud OpenFeign - okHTTP", executor.submit(openfeignServiceOkHttp::getJoke)
                ));
    }

    record ExtendedJokeCall(String serviceType, Future<Joke> jokeCall) {
    }

}
