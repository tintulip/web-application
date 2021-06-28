package com.tintulip.webapplication.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/addUser")
    public String addUser(TestUser user) {
        return "addUser";
    }


    @PostMapping("/addUser")
    public String proccessForm(TestUser user) {
        user.setId(UUID.randomUUID());
        user.setCreatedAt(Timestamp.from(Instant.now()));
        repository.save(user);
        return "showMessage";

    }


}

