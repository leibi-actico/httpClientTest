package com.actico.poc.httpclient.httpclienttest.config;

import com.actico.poc.httpclient.httpclienttest.service.RestClientService;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    @Value("${jokes.url}")
    private String URL;

    @Bean
    public RestClientService restClientService() {
        RestClient restClient = RestClient.builder().baseUrl(URL).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(RestClientService.class);
    }


    @Bean
    public RestClientService restClientServiceWithOkHTTP() {
        return Feign.builder().client(new OkHttpClient()).target(RestClientService.class, URL);
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}
