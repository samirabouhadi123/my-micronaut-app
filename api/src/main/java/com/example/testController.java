package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.HttpStatusException;

import java.util.concurrent.CompletableFuture;

@Controller("/core")
public class testController {
    // 404 Not Found
    @Get("/not-found")
    public HttpResponse<?> notFound() {
        throw new HttpStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    // 400 Bad Request
    @Get("/bad-request")
    public HttpResponse<?> badRequest() {
        throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Bad request error");
    }

    // 401 Unauthorized
    @Get("/unauthorized")
    public HttpResponse<?> unauthorized() {
        throw new HttpStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
    }

    // 403 Forbidden
    @Get("/forbidden")
    public HttpResponse<?> forbidden() {
        throw new HttpStatusException(HttpStatus.FORBIDDEN, "Forbidden access");
    }

    // 500 Internal Server Error
    @Get("/server-error")
    public HttpResponse<?> serverError() {
        throw new RuntimeException("Internal server error");
    }

    // Validation Error
    @Get("/validation/{input}")
    public HttpResponse<?> validation( @PathVariable String input) {
        return HttpResponse.ok("Validation passed for: " + input);
    }

    // Async Error
    @Get("/async-error")
    public CompletableFuture<String> asyncError() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Async operation failed"));
        return future;
    }
}
