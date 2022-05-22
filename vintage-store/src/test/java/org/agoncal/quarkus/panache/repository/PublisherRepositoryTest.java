package org.agoncal.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import javax.persistence.EntityNotFoundException;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void createAndFindPublisher() {
        long count = Publisher.count();
        final int listAll = Publisher.listAll().size();
        assertEquals(count, listAll);

        Publisher a = new Publisher("name");
        Publisher.persist(a);
        assertNotNull(a.id);

        assertEquals(count + 1, Publisher.count());

        a = Publisher.findById(a.id);
        assertTrue(Publisher.findContainsInName("am").size() > 0);
        a = Publisher.findByName("name").orElseThrow(EntityNotFoundException::new);
        assertEquals("name", a.name);

        Publisher.deleteById(a.id);
        assertEquals(count, Publisher.count());
    }
}
