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
@DataJpaTest(showSql=true)
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

}
