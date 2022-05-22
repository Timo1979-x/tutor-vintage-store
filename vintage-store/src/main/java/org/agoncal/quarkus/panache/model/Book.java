package org.agoncal.quarkus.panache.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
// @Table(name = "t_books")
public class Book extends Item {
    @Column(length = 15)
    public String isbn;

    @Column(name="nb_of_pages")
    public int nbOfPages;

    @Column(name="publication_date")
    public LocalDate publicationDate;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public Language language;

    @ManyToOne
    @JoinColumn(name = "publisher_fk")
    public Publisher publisher;
}
