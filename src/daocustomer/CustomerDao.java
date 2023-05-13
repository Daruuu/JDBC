package daocustomer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    int create(Customer customer) throws SQLException;

    void delete(int idCustomer);

    Customer read(int idCustomer);

    void update(Customer customer);

    List<Customer> getCustomers() throws SQLException;
}
