package p1;

import albumBasicJDBC.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public Genre searchGenderById(int generoId) {
        Genre genre = null;

        String query = "SELECT * FROM Genre WHERE GenreId = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, generoId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int genreId = rs.getInt("GenreId");
                String name = rs.getString("Name");

                genre = new Genre(genreId, name);
            }
            ps.close();
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.printf("GenreId %s found!\n", generoId);
        return genre;
    }
}
