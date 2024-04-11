package com.actico.poc.httpclient.httpclienttest.config;

import com.actico.poc.httpclient.httpclienttest.service.RestClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    public static final String URL = "https://official-joke-api.appspot.com/random_joke";

    @Bean
    public RestClientService restClientService() {
        RestClient restClient = RestClient.builder().baseUrl(URL).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(RestClientService.class);
    }


}
