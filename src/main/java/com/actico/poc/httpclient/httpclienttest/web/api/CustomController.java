package com.actico.poc.httpclient.httpclienttest.web.api;

import com.actico.poc.httpclient.httpclienttest.model.ExtendedJoke;
import com.actico.poc.httpclient.httpclienttest.service.JokeService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Observed
public class CustomController {


    private final JokeService jokeService;

    @GetMapping("/")
    ResponseEntity<List<ExtendedJoke>> get() {


        return ResponseEntity.ok(jokeService.getJokes());
    }

}
