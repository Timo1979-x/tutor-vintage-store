package org.agoncal.quarkus.jpa;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import java.sql.SQLException;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    public void createAndFindCustomer() {
        Customer a = new Customer("first", "last", "email");
        repository.persist(a);
        Assertions.assertNotNull(a.getId());

        a = repository.findById(a.getId());
        Assertions.assertEquals("first", a.getFirstName());
        Assertions.assertEquals("last", a.getLastName());
        Assertions.assertEquals("email", a.getEmail());
    }
}
