package pl.com.imralav.vxml.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.com.imralav.vxml.controllers.rest.CustomerRest;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.services.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRest.class)
public class CustomerRestIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void shouldFetchCustomers() throws Exception {
        // given
        Customer customer = prepareCustomer();
        given(customerService.findAll()).willReturn(Arrays.asList(customer));
        // when
        // then
        mockMvc.perform(get("/customers")).andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.*.id").exists())
               .andExpect(jsonPath("$.*.code").exists());
    }

    private Customer prepareCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCode(1000);
        return customer;
    }

    @Test
    public void shouldGenerateNewCustomer() throws Exception {
        // given
        Customer generatedCustomer = prepareCustomer();
        given(customerService.generateNewCustomer()).willReturn(generatedCustomer);
        // when
        // then
        mockMvc.perform(get("/customers/generate")).andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isMap())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.code").exists());
    }
}
