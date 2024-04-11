package com.actico.poc.httpclient.httpclienttest.model;

import lombok.Builder;

@Builder
public record ExtendedJoke(String serviceType, Joke joke) {
}
