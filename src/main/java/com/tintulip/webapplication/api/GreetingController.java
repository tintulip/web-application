package com.tintulip.webapplication.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GreetingController {
  static final String APPLICATION_JSON_HAL_VALUE = "application/hal+json";

    @GetMapping(path = "/greeting", produces = APPLICATION_JSON_HAL_VALUE)
    ResponseEntity<GreetingResource> greeting() {
        return ok(createHelloWorldGreetingWithLinks());
    }

    private GreetingResource createHelloWorldGreetingWithLinks() {
        return GreetingResource.builder().greeting("Greetings, CLA World!").build();
    }
}