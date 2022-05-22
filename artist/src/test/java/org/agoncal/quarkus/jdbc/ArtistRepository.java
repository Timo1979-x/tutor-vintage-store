package org.agoncal.quarkus.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class ArtistRepository {
    @Inject
    DataSource dataSource;

    public void persist(Artist artist) throws SQLException {
        final Connection connection = dataSource.getConnection();
        String sql = "Insert into t_artists (id, name, bio, created_date) values (?, ?, ?, ?)";
        artist.setId(new Random().nextLong());
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, artist.getId());
            ps.setString(2, artist.getName());
            ps.setString(3, artist.getBio());
            ps.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
            ps.executeUpdate();
        }
        connection.close();
    }

    public Artist findById(Long id) throws SQLException {
        final Connection connection = dataSource.getConnection();
        String sql = "Select id, name, bio, created_date from t_artists where id = ?";
        Artist a = new Artist();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                a.setId(rs.getLong(1));
                a.setName(rs.getString(2));
                a.setBio(rs.getString(3));
                a.setCreatedDate(rs.getTimestamp(4).toInstant());
            }
        }
        connection.close();

        return a;
    }
}
