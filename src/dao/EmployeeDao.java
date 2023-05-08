package dao;

import albumBasicJDBC.Album;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    int create(Employee employee) throws SQLException;
    void delete(int idEmployee) throws SQLException;
    Employee read(int idEmployee) throws SQLException;
    void update(Employee employee) throws SQLException;
    List<Employee> getEmployees() throws SQLException;
}
