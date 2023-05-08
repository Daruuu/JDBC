package dao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImplement  implements EmployeeDao{
    @Override
    public int create(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public void delete(int idEmployee) throws SQLException {

    }

    @Override
    public Employee read(int idEmployee) throws SQLException {
        return null;
    }

    @Override
    public void update(Employee employee) throws SQLException {

    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        return null;
    }
}
