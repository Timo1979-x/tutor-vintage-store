package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {
    @Column(length = 50, nullable = false)
    public String name;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public static Optional<Publisher> findByName(String name) {
        return Publisher.find("name", name).firstResultOptional();
    }

    public static List<Publisher> findContainsInName(String name) {
        return Publisher.list("name like ?1", "%" + name + "%");
    }
}
