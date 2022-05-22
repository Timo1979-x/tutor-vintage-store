package org.agoncal.quarkus.panache.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// @Table(name = "t_cds")
public class CD extends Item {
    @Column(name="music_company")
    public String musicCompany;

    @Column(length = 100)
    public String genre;

    @OneToMany(mappedBy = "cd")
    List<Track> tracks = new ArrayList<>();
}
