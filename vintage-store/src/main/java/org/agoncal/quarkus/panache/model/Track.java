package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.Duration;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_tracks")
public class Track extends PanacheEntity {
    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public Duration duration;

    @Column(nullable = false, name = "created_date")
    public Instant createdDate = Instant.now();

    @ManyToOne
    @JoinColumn(name="cd_fk")
    public CD cd;
}
