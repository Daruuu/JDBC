package daocustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerImplementDao implements CustomerDao {

    private static Connection connectionCustomer = ConexioCustomer.getConnection();

    @Override
    public int create(Customer customer) throws SQLException {
        int nuevoIdCustomer = -1;

        String sql = "INSERT INTO Customer(FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connectionCustomer.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCompany());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCity());
            ps.setString(6, customer.getState());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getPostalCode());
            ps.setString(9, customer.getPhone());
            ps.setString(10, customer.getFax());
            ps.setString(11, customer.getEmail());
            ps.setInt(12, customer.getSupportRepId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            nuevoIdCustomer = rs.getInt(1);

            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("error al insertar el customer " + e.getMessage());
        }
        return nuevoIdCustomer;
    }

    @Override
    public void delete(int idCustomer) {
        try {
            connectionCustomer.setAutoCommit(false);
            String sql = "DELETE * FROM Customer WHERE CustomerId = ?";

            PreparedStatement ps = connectionCustomer.prepareStatement(sql);
            ps.setInt(1, idCustomer);
            ps.executeUpdate();
            connectionCustomer.commit();
            System.out.println("eliminado correctamente con ID " + idCustomer);

        } catch (SQLException e) {
            System.out.println("idCustomer no existente " + e.getMessage());
        }
    }

    @Override
    public Customer read(int idCustomer) {
        Customer customerRead = null;
        String query = "SELECT * FROM Customer WHERE CustomerId = ?";
        try (PreparedStatement ps = connectionCustomer.prepareStatement(query)) {
            ps.setInt(1, idCustomer);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("Lastname");
                String company = rs.getString("Company");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String country = rs.getString("Country");
                String postalCode = rs.getString("PostalCode");
                String phone = rs.getString("Phone");
                String fax = rs.getString("Fax");
                String email = rs.getString("Email");
                int supportRepId = rs.getInt("SupportRepID");

                customerRead = new Customer(firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRepId);
            }

        } catch (SQLException e) {
            System.out.println(" ID customer no existe!" + e.getMessage());
        }

        return customerRead;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> getCustomers() throws SQLException {
        return null;
    }
}
