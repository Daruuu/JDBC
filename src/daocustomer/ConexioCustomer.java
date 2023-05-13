package daocustomer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioCustomer {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:Chinook_Sqlite.sqlite");
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            System.out.println("Opened database successfully!");
        }
        return connection;
    }
}
