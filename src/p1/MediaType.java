package p1;

import albumBasicJDBC.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaType {
    private int mediaTypeID;
    private String name;
    private static Connection con = Connexio.getConnection();

    public MediaType() {
    }

    public MediaType(int mediaTpeID, String name) {
        this.mediaTypeID = mediaTpeID;
        this.name = name;
    }

    public int getMediaTpeID() {
        return mediaTypeID;
    }

    public void setMediaTpeID(int mediaTpeID) {
        this.mediaTypeID = mediaTpeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "mediaTypeID=" + mediaTypeID +
                ", name='" + name + '\'' +
                '}';
    }

    public MediaType readMediaTypeById(int mediaTypeId) {

        MediaType mediaTypeObject = null;

        String query = "SELECT * FROM MediaType WHERE mediaType = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, mediaTypeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idMediaT = rs.getInt("MediaTypeid");
                String name = rs.getString("Name");
                mediaTypeObject = new MediaType(idMediaT, name);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Read mediaType done!");
        return mediaTypeObject;
    }
}
