package org.agoncal.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.inject.Inject;
import org.agoncal.quarkus.jdbc.Artist;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    @TestTransaction
    public void createAndFindArtist() {
        long count = repository.count();
        final int listAll = repository.listAll().size();
        assertEquals(count, listAll);
        assertEquals(repository.listAllArtistsSorted().size(), listAll);

        Artist a = new Artist("name", "bio");
        repository.persist(a);
        Assertions.assertNotNull(a.getId());

        assertEquals(count + 1, repository.count());

        a = repository.findById(a.getId());
        assertEquals("name", a.getName());

        repository.deleteById(a.getId());
        assertEquals(count, repository.count());
    }

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    public String jdbcUrl;
    @ConfigProperty(name = "quarkus.datasource.password")
    public String jdbcPassword;
    @ConfigProperty(name = "quarkus.datasource.username")
    public String jdbcUsername;

    @Test
    public void showProperties() {
        System.out.println(jdbcUrl);
        System.out.println(jdbcUsername);
        System.out.println(jdbcPassword);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try (FileOutputStream fos = new FileOutputStream("___db.properties");
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            Properties p = new Properties(3);
            p.put("quarkus.datasource.jdbc.url", jdbcUrl);
            p.put("quarkus.datasource.password", jdbcPassword);
            p.put("quarkus.datasource.username", jdbcUsername);
            p.store(osw, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
