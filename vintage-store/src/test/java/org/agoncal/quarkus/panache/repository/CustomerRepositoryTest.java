package org.agoncal.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    public void createAndFindCustomer() {
        assertTrue(repository.listAllDans().size() <= repository.count());
        Customer a = new Customer("first", "last", "email");
        repository.persist(a);
        Assertions.assertNotNull(a.getId());

        a = repository.findById(a.getId());
        Assertions.assertEquals("first", a.getFirstName());
        Assertions.assertEquals("last", a.getLastName());
        Assertions.assertEquals("email", a.getEmail());
    }
}
