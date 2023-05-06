package p1;

import albumBasicJDBC.Album;
import albumBasicJDBC.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Track {
    private int trackId;
    private String name;
    private int idAlbum;
    private int idMediaType;
    private int idGenre;
    private String composer;
    private int milliseconds;
    private int bytes;
    private float unitPrice;

    public static Connection connection = Connexio.getConnection();

    public Track() {
    }

    public Track(String name, int idAlbum, int idMediaType, int idGenre, String composer, int milliseconds, int bytes, float unitPrice) {
        this.name = name;
        this.idAlbum = idAlbum;
        this.idMediaType = idMediaType;
        this.idGenre = idGenre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdMediaType() {
        return idMediaType;
    }

    public void setIdMediaType(int idMediaType) {
        this.idMediaType = idMediaType;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", name='" + name + '\'' +
                ", idAlbum=" + idAlbum +
                ", idMediaType=" + idMediaType +
                ", idGenre=" + idGenre +
                ", composer=" + composer +
                ", milliseconds=" + milliseconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                '}';
    }

    boolean comprobarFKTrack(int idAlbumFk, int idGenreFk, int idMediaTypeIdFk) throws SQLException {

        String queryAlbum = "SELECT * FROM Album WHERE AlbumId = ?";
        String queryMediaType = "SELECT * FROM MediaType WHERE MediaTypeId = ?";
        String queryGenre = "SELECT * FROM Genre WHERE GenreId = ?";

        PreparedStatement psAlbum = connection.prepareStatement(queryAlbum);
        PreparedStatement psMediaT = connection.prepareStatement(queryMediaType);
        PreparedStatement psGenre = connection.prepareStatement(queryGenre);

        psAlbum.setInt(1, idAlbumFk);
        psMediaT.setInt(1, idMediaTypeIdFk);
        psGenre.setInt(1, idGenreFk);

        try {
            ResultSet rsAlbum = psAlbum.executeQuery();
            ResultSet rsMedia = psMediaT.executeQuery();
            ResultSet rsGenre = psGenre.executeQuery();

            if (rsAlbum.next() && rsMedia.next() && rsGenre.next()) {
                return true;
            } else {
                throw new SQLException("Alguna de las comprobaciones no existe !");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
        return true;
    }

    public int createTrack(String nameTrack, String composer, int milliseconds, int bytesTrack, float unitPriceTrack) throws SQLException {
        int nuevoIdAlbum = -1;
        String query = "INSERT INTO Track(Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nameTrack);
            ps.setString(2, composer);
            ps.setInt(3, milliseconds);
            ps.setInt(4, bytesTrack);
            ps.setDouble(5, unitPriceTrack);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            nuevoIdAlbum = rs.getInt(1);
            rs.next();

            ps.close();
            System.out.println("new record created!");
            return nuevoIdAlbum;

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return nuevoIdAlbum;
    }

    public Track readTrack(int idTrack) throws SQLException {

        Track track = null;
        String query = "SELECT * FROM Track WHERE TrackId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idTrack);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //fk de la tabla Track
                int idAlbumFk = rs.getInt("AlbumId");
                int idMediaTypeFk = rs.getInt("MediaTypeId");
                int idGenreFk = rs.getInt("GenreId");

                // atributos de la tabla Track
                String name = rs.getString("Name");
                String composer = rs.getString("Composer");
                int milliseconds = rs.getInt("Milliseconds");
                int bytes = rs.getInt("bytes");
                float unitPrice = rs.getFloat("UnitPrice");

                Album a = new Album();
                int idAlbumFKFunction = a.llegeixAlbum(idAlbumFk).getIdAlbum();

                MediaType mT = new MediaType();
                int idMediaFkFunction = mT.readMediaTypeById(idMediaTypeFk).getMediaTpeID();

                Genre g = new Genre();
                int idGenreFkFunction = g.buscarGenrePorId(idGenreFk).getGenreID();

                track = new Track(name, idAlbumFKFunction, idMediaFkFunction, idGenreFkFunction, composer, milliseconds, bytes, unitPrice);
            }
        }
        return track;
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("introduce idtrack: ");
        int idTrack = sc.nextInt();
        Track newTrack = new Track();
        newTrack.readTrack(idTrack);

    }
}