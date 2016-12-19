package pl.com.imralav.vxml.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pl.com.imralav.vxml.entities.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldGetCorrectCustomer() {
        //given
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCode(1239);
        Customer save = entityManager.persist(expectedCustomer);
        //when
        Customer actualCustomer = customerRepository.findOne(save.getId());
        //then
        assertThat(actualCustomer).isEqualTo(expectedCustomer);
    }


    @Test
    public void shouldCorrectlyCheckCodeExistance() {
        //given
        //when
        //then
        assertThat(customerRepository.doesExistForCode(1234)).isTrue();
        assertThat(customerRepository.doesExistForCode(9999)).isFalse();
    }

    @Test
    public void shouldCorrectlyDeleteByCustomerCode() {
        //given
        Customer customer = new Customer();
        customer.setCode(8888);
        customer = customerRepository.save(customer);
        assertThat(customerRepository.doesExistForCode(8888)).isTrue();
        //when
        customerRepository.deleteByCode(8888);
        //then
        assertThat(customerRepository.doesExistForCode(8888)).isFalse();
    }

}
