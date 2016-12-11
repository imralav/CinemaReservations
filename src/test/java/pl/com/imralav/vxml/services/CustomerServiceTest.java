package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.repositories.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService instance;

    @Test
    public void shouldDelegateFindAllToRepository() {
        //given
        //when
        instance.findAll();
        //then
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void shouldGenerateNewCustomer() {
        //given
        given(customerRepository.save(any(Customer.class))).willAnswer(AdditionalAnswers.returnsFirstArg());
        given(customerRepository.doesExistForCode(anyInt())).willReturn(true, false);
        //when
        Customer generatedCustomer = instance.generateNewCustomer();
        //then
        assertThat(generatedCustomer.getCode()).isNotNull();
        verify(customerRepository, times(2)).doesExistForCode(anyInt());
    }
}
