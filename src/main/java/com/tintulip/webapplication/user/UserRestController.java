package com.tintulip.webapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/1857b1ed-026e-4c38-bc6c-c1a171cbc38f")
    public Iterable<TestUser> nettitudeTest() {
        return repository.findAll();
    }
}