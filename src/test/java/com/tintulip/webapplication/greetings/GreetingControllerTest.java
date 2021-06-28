package com.tintulip.webapplication.greetings;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@Disabled
public class GreetingControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void shouldReturnHelloWorld() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/greeting")
        .accept(GreetingController.APPLICATION_JSON_HAL_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").value("http://localhost/greeting"))
        .andExpect(jsonPath("$.greeting").value("Greetings, CLA World!"));
  }
}