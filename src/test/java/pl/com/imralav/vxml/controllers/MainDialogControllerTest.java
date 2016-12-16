package pl.com.imralav.vxml.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MainDialogController.class)
public class MainDialogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void shouldReturnMainDialog() throws Exception {
        mockMvc.perform(get("/main-dialog")).andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML));
    }

    @Test
    @Ignore
    public void shouldReturnFarewell() throws Exception {
        mockMvc.perform(get("/farewell")).andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML));
    }
}
