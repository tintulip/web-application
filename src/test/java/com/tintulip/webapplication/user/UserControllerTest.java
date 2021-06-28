package com.tintulip.webapplication.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository repository;


    @Test
    public void shouldAddUser() throws Exception {
        String emailAddress = "test@example.com";
        String reason = "TINTULIP";
        String type   = "CREATIVE";

        String userAsJson = "{ \"emailAddress\": \"" + emailAddress + "\", " +
                "\"reason\": \"" + reason + "\", " +
                "\"type\": \"" + type + "\" " +

                "}";

        mvc.perform(MockMvcRequestBuilders.post("/addUser")
                .content(userAsJson))
                .andExpect(status().isOk());
    }
}
