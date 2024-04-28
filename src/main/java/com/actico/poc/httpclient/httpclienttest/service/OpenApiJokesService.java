package com.actico.poc.httpclient.httpclienttest.service;

import org.springframework.stereotype.Service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import com.actico.poc.httpclient.jokes.client.api.JokesApi;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;

@Service
@Observed
@RequiredArgsConstructor
public class OpenApiJokesService
{
   private final JokesApi jokesApi;

   public Joke getJoke()
   {
      return map(jokesApi.getRandomJoke());
   }

   private Joke map(com.actico.poc.httpclient.jokes.client.model.Joke apiJoke)
   {
      return new Joke(apiJoke.getType(), apiJoke.getSetup(), apiJoke.getPunchline(), apiJoke.getId());
   }
}
