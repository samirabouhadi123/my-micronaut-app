package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.exceptions.HttpStatusException;

@Controller("/core")
public class TestController {

    @Get("/bad-request")
    public HttpResponse<?> badRequest() {
        throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Bad request error");
    }
    @Get("/serverError")
    public HttpResponse<?> serverError() {
        throw new RuntimeException("Internal server error");
    }
}
