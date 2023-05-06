package p1;

import albumBasicJDBC.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre {
    private int genreID;
    private String name;

    public static Connection connection = Connexio.getConnection();

    public Genre() {
    }

    public Genre(int genreID, String name) {
        this.genreID = genreID;
        this.name = name;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreID=" + genreID +
                ", name='" + name + '\'' +
                '}';
    }

    public Genre buscarGenrePorId(int generoId) throws SQLException {
        Genre genre = null;

        String query = "SELECT * FROM Genre WHERE Genreid = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, generoId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int generId = rs.getInt("GenreId");
                String name = rs.getString("Name");

                genre = new Genre(generId, name);
            }

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("operation done!");
        return genre;
    }
}
