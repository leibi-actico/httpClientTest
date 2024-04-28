package com.actico.poc.httpclient.httpclienttest.service;

import org.springframework.stereotype.Service;

import com.actico.poc.httpclient.httpclienttest.model.Joke;
import com.actico.poc.httpclient.jokes.client.ApiClient;
import com.actico.poc.httpclient.jokes.client.api.DefaultApi;

@Service
public class OpenApiJokesService
{
   ApiClient apiClient = new ApiClient();


   public Joke getJoke()
   {
      var apiJoke = apiClient.buildClient(DefaultApi.class).randomJokeGet();
      return map(apiJoke);
   }

   private Joke map(com.actico.poc.httpclient.jokes.client.model.Joke apiJoke)
   {
      return new Joke(apiJoke.getType(), apiJoke.getSetup(), apiJoke.getPunchline(), apiJoke.getId());
   }
}
