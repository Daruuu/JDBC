package albumBasicJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Album {

    private int idAlbum;
    private String titol;
    private Artist artist;
    private static Connection con = Connexio.getConnection();

    public Album() {
    }

    public Album(int idAlbum, String titol, Artist artist) {
        this.idAlbum = idAlbum;
        this.titol = titol;
        this.artist = artist;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "idAlbum=" + idAlbum +
                ", titol='" + titol + '\'' +
                ", artist=" + artist +
                '}';
    }

    public int creaAlbum(String titol, int idArtista) {
        Statement stmt = null;
        int idAlbumNou = -1;
        try {
            //Creem la consulta de la PreparedStatement
            String query = "INSERT INTO Album (Title,ArtistId) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            //Modifiquem i executem la PreparedStatement
            ps.setString(1, titol);
            ps.setInt(2, idArtista);
            ps.executeUpdate();

            // Obtenim claus autogenerades
            ResultSet rs = ps.getGeneratedKeys();
            rs.next(); // Sabem que només n'hi ha una
            idAlbumNou = rs.getInt(1);

            ps.close();
            System.out.println("Records created successfully!");
            return idAlbumNou;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return idAlbumNou;
    }

    public Album searchAlbumById(int idAlbum) {
//        Statement stmt = null;
        Album album = null;
        try {
            String query = "SELECT * FROM Album WHERE AlbumId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlbum);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                Artist artist1 = new Artist();
                album = new Album(albumId, title, artist1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.printf("AlbumId %s found!\n", idAlbum);
        return album;
    }

    //EXER 1.2
    public Album leerAlbumCompleto(int idAlbum) {
        String query = "SELECT Album.*,Artist.* FROM " +
                "Album JOIN Artist ON Album.idArtista = Artist.artistId " +
                "WHERE Album.idAlbum = ?";
        Album albumCompleto = null;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idAlbum);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //datos del Album
                int getIdAlbum = rs.getInt("AlbumId");
                String titutoAlbum = rs.getString("Title");

                // datos del Artista
                int getidArtista = rs.getInt("ArtistID");
                String nombreArtista = rs.getString("Name");

                Artist artist1 = new Artist(getidArtista, nombreArtista);

                albumCompleto = new Album(getIdAlbum, titutoAlbum, artist1);
            }
            ps.close();
            rs.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
        return albumCompleto;
    }

    public void modificaAlbum(int idAlbum, String nouTitol, int nouIdArtista) {
        Statement stmt = null;
        try {
            con.setAutoCommit(false);
            String query = "UPDATE Album set Title = ?, ArtistId = ? WHERE AlbumId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, nouTitol);
            ps.setInt(2, nouIdArtista);
            ps.setInt(3, idAlbum);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    public void eliminaAlbum(int idAlbum) {
        try {
            con.setAutoCommit(false);
            String query = "DELETE from Album where AlbumId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlbum);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    public List<Album> seleccionaAlbums() {
        Statement stmt = null;
        List<Album> albums = new ArrayList<>();
        try {
            //Crear una consulta / query amb un object Statement
            stmt = con.createStatement();
            //Executar la consulta
            ResultSet rs = stmt.executeQuery("SELECT * FROM Album;");
            //Procesar el resultat amb l’objecte ResultSet
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                Artist artist1 = new Artist();
                albums.add(new Album(albumId, title, artist1));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
        return albums;
    }
}
