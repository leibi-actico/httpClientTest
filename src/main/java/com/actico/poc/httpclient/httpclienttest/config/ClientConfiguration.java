package com.actico.poc.httpclient.httpclienttest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.actico.poc.httpclient.httpclienttest.service.OkHttpService;
import com.actico.poc.httpclient.httpclienttest.service.RestClientService;
import com.actico.poc.httpclient.jokes.client.ApiClient;
import com.actico.poc.httpclient.jokes.client.api.JokesApi;

import feign.Feign;
import feign.okhttp.OkHttpClient;

@Configuration
public class ClientConfiguration
{

   @Value("${jokes.url}")
   private String URL;

   @Bean
   public RestClientService restClientService()
   {
      RestClient restClient = RestClient.builder().baseUrl(URL).build();
      RestClientAdapter adapter = RestClientAdapter.create(restClient);

      HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
      return factory.createClient(RestClientService.class);
   }


   @Bean
   public OkHttpService okHttpService()
   {
      return Feign.builder()
         .client(new OkHttpClient())
         .decoder(new feign.jackson.JacksonDecoder())
         .target(OkHttpService.class, URL);
   }

   @Bean
   public OkHttpClient client()
   {
      return new OkHttpClient();
   }


   @Bean
   public JokesApi jokesApi()
   {
      return new ApiClient().buildClient(JokesApi.class);
   }

}
