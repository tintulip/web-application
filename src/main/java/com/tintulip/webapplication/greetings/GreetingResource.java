package com.tintulip.webapplication.greetings;

import com.tintulip.webapplication.greetings.GreetingController;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Value
@EqualsAndHashCode(callSuper = true)
@Builder
class GreetingResource extends RepresentationModel {

    private final String greeting;

    private GreetingResource(String greeting) {
        this.greeting = greeting;

        add(linkTo(methodOn(GreetingController.class).greeting()).withSelfRel());
    }

}