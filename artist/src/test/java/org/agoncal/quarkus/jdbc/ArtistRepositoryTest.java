package org.agoncal.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import java.sql.SQLException;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    public void createAndFindArtist() throws SQLException {
        Artist a = new Artist("name", "bio");
        repository.persist(a);
        Assertions.assertNotNull(a.getId());

        a = repository.findById(a.getId());
        Assertions.assertEquals("name", a.getName());
    }
}
